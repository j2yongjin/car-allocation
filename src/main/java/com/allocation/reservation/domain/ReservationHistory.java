package com.allocation.reservation.domain;

import com.allocation.support.converter.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation_his")
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor()
public class ReservationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_his_no" , nullable = false)
    private Long reservationHisNo;

    @Column(name = "reserve_id",nullable = false)
    private String reserveId;

    @Column(name = "status",length = 10,nullable = false)
    @Enumerated(EnumType.STRING)
    private ReserveStatus status;

    @Column(name = "reg_date",nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime regDate;

    public ReservationHistory(String reserveId, ReserveStatus status) {
        this.reserveId = reserveId;
        this.status = status;
        this.regDate = LocalDateTime.now();
    }
}
