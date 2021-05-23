package com.allocation.driver.domain;

import com.allocation.driver.model.DriverAllocateRequest;
import com.allocation.reservation.domain.ReserveStatus;
import com.allocation.support.converter.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver_allocate_his")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor()
public class AllocateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allocate_no" , nullable = false)
    private Long allocateNo;

    @Column(name="request_id" , length = 20, nullable = false)
    private String requestId;

    @Column(name = "request_dttm" , nullable = false)
    @Convert(converter = LocalDateTimeConverter.class )
    private LocalDateTime requestDatetime;

    @Column(name = "reserve_id",length = 28 , nullable = false)
    private String reserveId;

    @Column(name = "driver_name" , length = 100 , nullable = false)
    private String driverName;

    @Column(name = "driver_phone" , length = 20 , nullable = false)
    private String driverTelephone;

    @Column(name = "car_id" ,length = 30 , nullable = false)
    private String carId;

    @Column(name = "status" , length = 10 , nullable = false)
    @Enumerated(EnumType.STRING)
    private ReserveStatus status;

    @Column(name = "rsv_st_dt" ,nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime reserveStDttm;

    @Column(name = "rsv_en_dt" ,nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime reserveEndDttm;

    @Column(name = "start_dt" )
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime startDatetime;

    @Column(name = "end_dt" )
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime endDatetime;


    @Column(name="reg_dt",nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime regDatetime;

    @Column(name = "mod_dt")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifyDatetime;

    public Boolean isStartable(){
        return status.equals(ReserveStatus.ALLOCATED);
    }

    public Boolean isStoppable(){
        return status.equals(ReserveStatus.DRIVING);
    }

    public void start(LocalDateTime startDt){
        this.startDatetime = startDt;
        this.status = ReserveStatus.DRIVING;
        this.modifyDatetime = LocalDateTime.now();
    }

    public void withdraw(){
        this.status = ReserveStatus.WITHDRAW;
        this.modifyDatetime = LocalDateTime.now();
    }

    public void complete(LocalDateTime endDt){
        this.endDatetime = endDt;
        this.status = ReserveStatus.COMPLETED;
        this.modifyDatetime = LocalDateTime.now();
    }



}
