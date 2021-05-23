package com.allocation.driver.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAllocateHistory is a Querydsl query type for AllocateHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAllocateHistory extends EntityPathBase<AllocateHistory> {

    private static final long serialVersionUID = -955096302L;

    public static final QAllocateHistory allocateHistory = new QAllocateHistory("allocateHistory");

    public final NumberPath<Long> allocateNo = createNumber("allocateNo", Long.class);

    public final StringPath carId = createString("carId");

    public final StringPath driverName = createString("driverName");

    public final StringPath driverTelephone = createString("driverTelephone");

    public final DateTimePath<java.time.LocalDateTime> endDatetime = createDateTime("endDatetime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDatetime = createDateTime("modifyDatetime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regDatetime = createDateTime("regDatetime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> requestDatetime = createDateTime("requestDatetime", java.time.LocalDateTime.class);

    public final StringPath requestId = createString("requestId");

    public final DateTimePath<java.time.LocalDateTime> reserveEndDttm = createDateTime("reserveEndDttm", java.time.LocalDateTime.class);

    public final StringPath reserveId = createString("reserveId");

    public final DateTimePath<java.time.LocalDateTime> reserveStDttm = createDateTime("reserveStDttm", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startDatetime = createDateTime("startDatetime", java.time.LocalDateTime.class);

    public final EnumPath<com.allocation.reservation.domain.ReserveStatus> status = createEnum("status", com.allocation.reservation.domain.ReserveStatus.class);

    public QAllocateHistory(String variable) {
        super(AllocateHistory.class, forVariable(variable));
    }

    public QAllocateHistory(Path<? extends AllocateHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAllocateHistory(PathMetadata metadata) {
        super(AllocateHistory.class, metadata);
    }

}

