package com.allocation.lookup.infra;

import com.allocation.lookup.domain.ReservationDetail;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.allocation.driver.domain.QAllocateHistory.*;
import static com.allocation.reservation.domain.QReservation.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReservationLookupRepositoryImpl implements ReservationLookupRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReservationDetail> findAll() {

        try {
            return queryFactory.select(Projections.fields(ReservationDetail.class
                    , reservation.reserveId.as("reserveId")
                    ,reservation.status
                    ,reservation.startDatetime.as("rsvStartDttm")
                    ,reservation.endDatetime.as("rsvEndDttm")
                    ,reservation.departure,reservation.destination
                    ,reservation.userName.as("memberName")
                    ,reservation.userTelephone.as("memberTelephone")
                    ,allocateHistory.driverName,allocateHistory.driverTelephone
                    ,allocateHistory.carId,allocateHistory.startDatetime.as("driveStartDttm")
                    ,allocateHistory.endDatetime.as("driveEndDttm")
                    )
            ).from(reservation).leftJoin(allocateHistory)
                    .on(reservation.reserveId.eq(allocateHistory.reserveId))
                    .fetch();
        }catch (Exception e){
            log.info("",e);
            throw e;
        }
    }

}
