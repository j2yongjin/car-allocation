package com.allocation.reservation.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservationHistory is a Querydsl query type for ReservationHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReservationHistory extends EntityPathBase<ReservationHistory> {

    private static final long serialVersionUID = 391008079L;

    public static final QReservationHistory reservationHistory = new QReservationHistory("reservationHistory");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> reservationHisNo = createNumber("reservationHisNo", Long.class);

    public final StringPath reserveId = createString("reserveId");

    public final EnumPath<ReserveStatus> status = createEnum("status", ReserveStatus.class);

    public QReservationHistory(String variable) {
        super(ReservationHistory.class, forVariable(variable));
    }

    public QReservationHistory(Path<? extends ReservationHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservationHistory(PathMetadata metadata) {
        super(ReservationHistory.class, metadata);
    }

}

