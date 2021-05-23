package com.allocation.reservation.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 897834373L;

    public static final QReservation reservation = new QReservation("reservation");

    public final StringPath departure = createString("departure");

    public final StringPath destination = createString("destination");

    public final DateTimePath<java.time.LocalDateTime> endDatetime = createDateTime("endDatetime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDatetime = createDateTime("modifyDatetime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regDatetime = createDateTime("regDatetime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> requestDatetime = createDateTime("requestDatetime", java.time.LocalDateTime.class);

    public final StringPath requestId = createString("requestId");

    public final StringPath reserveId = createString("reserveId");

    public final DateTimePath<java.time.LocalDateTime> startDatetime = createDateTime("startDatetime", java.time.LocalDateTime.class);

    public final EnumPath<ReserveStatus> status = createEnum("status", ReserveStatus.class);

    public final StringPath userName = createString("userName");

    public final StringPath userTelephone = createString("userTelephone");

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

