package com.allocation.lookup.model;

import com.allocation.lookup.domain.ReservationDetail;
import com.allocation.reservation.domain.ReserveStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
public class ReservationsResponse {

    List<Reservation> reservations;


    @NoArgsConstructor
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Reservation {

        private String identifier;
        private ReserveStatus status;
        private Time reservedTime;
        private Location location;
        private Member member;
        private Driver driver;
        private Car car;

    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Time {
        private LocalDate startDate;
        private LocalTime startTime;
        private Integer estimatedTime;

        public static Time of(LocalDate startDate , LocalTime startTime , Integer estimatedTime){
            return new Time(startDate,startTime,estimatedTime);
        }
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Location{
        private String departure;
        private String destination;
        public static Location of(String departure , String destination){
            return new Location(departure,destination);
        }
    }


    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Member {
        private String name;
        private String telephone;
        public static Member of(String name,String telephone){
            return new Member(name,telephone);
        }
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Driver {
        private String name;
        private String telePhone;
        private LocalDateTime startDttm;
        private LocalDateTime endDttm;

        public static Driver of(String name , String telePhone , LocalDateTime startDttm , LocalDateTime endDttm){
            return new Driver(name,telePhone,startDttm,endDttm);
        }
    }

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Car {
        private String identifier;
        public static Car of(String identifier){
            return new Car(identifier);
        }
    }

    public static ReservationsResponse of(List<ReservationDetail> reservationDetails){

        List<Reservation> reservations = reservationDetails.stream().map(
                s -> Reservation.builder()
                        .identifier(s.getReserveId())
                        .status(s.getStatus())
                        .member(Member.of(s.getMemberName(),s.getMemberTelephone()))
                        .car(Car.of(s.getCarId()))
                        .location(Location.of(s.getDeparture(),s.getDestination()))
                        .driver(Driver.of(s.getDriverName()
                                ,s.getDriverTelephone()
                                ,s.getDriveStartDttm()
                                ,s.getDriveEndDttm()))
                        .reservedTime(Time.of(s.getRsvStartDttm().toLocalDate()
                                 ,s.getRsvStartDttm().toLocalTime()
                                 ,(int)(Duration.between(s.getRsvStartDttm(),s.getRsvEndDttm()).getSeconds()/60L)))
                        .build()
        ).collect(Collectors.toList());

        return new ReservationsResponse(reservations);
    }

}
