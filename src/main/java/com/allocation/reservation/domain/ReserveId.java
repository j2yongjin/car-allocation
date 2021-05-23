package com.allocation.reservation.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class ReserveId implements Serializable {

    private final ReserveType type;
    private final String randomValue;
    private final String incrementValue;

    @JsonValue
    private String reserveId;

    ReserveId(ReserveType type, String randomValue, String incrementValue) {
        this.type = type;
        this.randomValue = randomValue;
        this.incrementValue = incrementValue;
        this.reserveId = type.getValue() + randomValue + incrementValue;
    }

    public static ReserveId of(ReserveType type, String randomValue, String incrementValue){
        return new ReserveId(type,randomValue,incrementValue);
    }

    public String getReserveId() {
        return reserveId;
    }
}
