package com.allocation.driver.model;

import com.allocation.application.RequestValidate;
import com.allocation.application.ServiceException;
import com.allocation.support.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class DriverStopRequest implements RequestValidate {

    private Reservation reservation;
    private LocalDateTime endDatetime;

    @Getter
    @NoArgsConstructor
    @ToString
    public static class  Reservation {
        private String identifier;
    }

    @Override
    public void validate() {
        if(Objects.isNull(reservation)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(reservation) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(reservation.identifier)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(reservation.identifier) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(endDatetime)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(endDatetime) 파마미터가 유효하지 않습니다.");
    }
}
