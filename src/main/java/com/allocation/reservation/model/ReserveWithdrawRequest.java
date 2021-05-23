package com.allocation.reservation.model;

import com.allocation.application.RequestValidate;
import com.allocation.application.ServiceException;
import com.allocation.support.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.Objects;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class ReserveWithdrawRequest implements RequestValidate {

    private Member member;
    private Reservation reservation;

    @Override
    public void validate() {
        if(Objects.isNull(member)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(member) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(member.name)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(member.name) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(member.telephone)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(member.telephone) 파마미터가 유효하지 않습니다.");

        if(Objects.isNull(reservation)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(reservation) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(reservation.identifier)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(reservation.identifier) 파마미터가 유효하지 않습니다.");
    }


    @NoArgsConstructor
    @Getter
    @ToString
    public static class Member {
        private String name;
        private String telephone;
    }

    @NoArgsConstructor
    @Getter
    @ToString
    public static class Reservation {
        private String identifier;
    }



}
