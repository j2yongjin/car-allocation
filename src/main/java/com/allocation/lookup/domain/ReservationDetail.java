package com.allocation.lookup.domain;


import com.allocation.reservation.domain.ReserveStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationDetail {

    private String reserveId;
    private ReserveStatus status;
    private LocalDateTime rsvStartDttm;
    private LocalDateTime rsvEndDttm;
    private String departure;
    private String destination;
    private String memberName;
    private String memberTelephone;
    private String driverName;
    private String driverTelephone;
    private String carId;
    private LocalDateTime driveStartDttm;
    private LocalDateTime driveEndDttm;

}
