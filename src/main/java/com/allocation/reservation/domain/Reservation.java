package com.allocation.reservation.domain;

import com.allocation.support.converter.LocalDateTimeConverter;
import com.allocation.support.converter.ReserveIdConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor()
public class Reservation {
    @Id
    @Column(name="reserve_id" , length = 28 , nullable = false)
    private String reserveId;

    @Column(name="request_id" , length = 20, nullable = false)
    private String requestId;

    @Column(name = "request_dttm" , nullable = false)
    @Convert(converter = LocalDateTimeConverter.class )
    private LocalDateTime requestDatetime;

    @Column(name = "user_name" ,length = 30,nullable = false)
    private String userName;

    @Column(name = "user_telephone " , length = 20 , nullable = false)
    private String userTelephone;

    @Column(name = "start_dt" , nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime startDatetime;

    @Column(name = "end_dt" , nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime endDatetime;

    @Column(name = "departure" , length = 300 , nullable = false)
    private String departure;

    @Column(name = "destination" , length = 300 , nullable = false)
    private String destination;

    @Column(name = "status" , length = 10 , nullable = false)
    @Enumerated(EnumType.STRING)
    private ReserveStatus status;

    @Column(name="reg_dt",nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime regDatetime;

    @Column(name = "mod_dt")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifyDatetime;

    public Boolean isAllocatable(){
        return (status.equals(ReserveStatus.READY));
    }
    public Boolean isWithdrawable(String name , String telephone,LocalDateTime withdrawableTime){

        if(!name.equals(this.userName) || !telephone.equals(this.userTelephone)) return Boolean.FALSE;
        if(ReserveStatus.READY.equals(status)) return Boolean.TRUE;
        if(ReserveStatus.ALLOCATED.equals(status) && withdrawableTime.isAfter(modifyDatetime)) return Boolean.TRUE;

        return Boolean.FALSE;
    }

    public void allocate(){
        this.status = ReserveStatus.ALLOCATED;
        this.modifyDatetime = LocalDateTime.now();
    }

    public void start() {
        this.status = ReserveStatus.DRIVING;
        this.modifyDatetime = LocalDateTime.now();
    }

    public void withdraw(){
        this.status = ReserveStatus.WITHDRAW;
        this.modifyDatetime = LocalDateTime.now();
    }

    public void complete(){
        this.status = ReserveStatus.COMPLETED;
        this.modifyDatetime = LocalDateTime.now();
    }
}
