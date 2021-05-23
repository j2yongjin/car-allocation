package com.allocation.driver.model;

import com.allocation.application.RequestValidate;
import com.allocation.application.ServiceException;
import com.allocation.driver.domain.AllocateHistory;
import com.allocation.reservation.domain.ReserveStatus;
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
public class DriverAllocateRequest implements RequestValidate {

    private LocalDateTime requestDatetime;
    private String requestId;
    private Reservation reservation;
    private Driver driver;
    private Car car;


    @Override
    public void validate() {

        if(Objects.isNull(requestDatetime)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(requestDatetime) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(requestId)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(requestId) 파마미터가 유효하지 않습니다.");
        if(Objects.isNull(reservation)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(reservation) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(reservation.identifier)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(reservation.identifier) 파마미터가 유효하지 않습니다.");

        if(Objects.isNull(driver)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(member) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(driver.name)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(driver.name) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(driver.telephone)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(driver.telephone) 파마미터가 유효하지 않습니다.");

        if(Objects.isNull(car)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(car) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(car.identifier)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(car.identifier) 파마미터가 유효하지 않습니다.");
    }

    @NoArgsConstructor
    @Getter
    @ToString
    public static class Driver {
        private String name;
        private String telephone;
    }

    @NoArgsConstructor
    @Getter
    @ToString
    public static class Car {
        private String identifier;
    }

    @NoArgsConstructor
    @Getter
    @ToString
    public static class Reservation {
        private String identifier;
    }

    public AllocateHistory getAllocateHistory(String reserveId , LocalDateTime stDttm , LocalDateTime endDttm){

        return AllocateHistory.builder()
                .reserveId(reserveId)
                .reserveStDttm(stDttm)
                .reserveEndDttm(endDttm)
                .requestDatetime(this.requestDatetime)
                .requestId(this.requestId)
                .driverName(this.driver.name)
                .driverTelephone(this.driver.telephone)
                .carId(car.identifier)
                .regDatetime(LocalDateTime.now())
                .status(ReserveStatus.ALLOCATED)
                .build();
    }


}
