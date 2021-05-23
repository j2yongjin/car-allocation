package com.allocation.reservation.model;

import com.allocation.application.RequestValidate;
import com.allocation.application.ServiceException;
import com.allocation.reservation.domain.Reservation;
import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.domain.ReserveStatus;
import com.allocation.support.error.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class ReserveRequest implements RequestValidate {

    private LocalDateTime requestDatetime;
    private String requestId;
    private Member member;
    private Time time;
    private Location location;

    @Override
    public void validate() {
        if(Objects.isNull(requestDatetime)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(requestDatetime) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(requestId)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(requestId) 파마미터가 유효하지 않습니다.");
        if(Objects.isNull(member)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(member) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(member.name)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(member.name) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(member.telephone)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(member.telephone) 파마미터가 유효하지 않습니다.");

        if(Objects.isNull(time)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(time) 파마미터가 유효하지 않습니다.");
        if(Objects.isNull(time.startDate)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(time.startDate) 파마미터가 유효하지 않습니다.");
        if(Objects.isNull(time.startTime)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(time.startTime) 파마미터가 유효하지 않습니다.");
        if(time.estimatedTime <=0) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(time.estimatedTime) 파마미터가 유효하지 않습니다.");

        if(Objects.isNull(location)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(time) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(location.departure)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(time.departure) 파마미터가 유효하지 않습니다.");
        if(StringUtils.isEmpty(location.destination)) throw new ServiceException(ErrorCode.INVALID_PARAMETER,"(time.destination) 파마미터가 유효하지 않습니다.");
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
    public static class Time {
        private LocalDate startDate;
        private LocalTime startTime;
        private Integer estimatedTime;
    }

    @NoArgsConstructor
    @Getter
    @ToString
    public static class Location {
        private String departure;
        private String destination;
    }

    public Reservation getReservation(ReserveId reserveId, ReserveStatus status){
        return Reservation.builder()
                .userTelephone(member.telephone)
                .userName(member.name)
                .requestId(requestId)
                .requestDatetime(requestDatetime)
                .status(status)
                .regDatetime(LocalDateTime.now())
                .startDatetime(LocalDateTime.of(time.startDate,time.startTime))
                .endDatetime(LocalDateTime.of(time.startDate,time.startTime).plusMinutes(time.estimatedTime))
                .departure(location.departure)
                .destination(location.destination)
                .reserveId(reserveId.getReserveId())
                .build();
    }


}
