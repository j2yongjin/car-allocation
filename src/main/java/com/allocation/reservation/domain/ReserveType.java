package com.allocation.reservation.domain;

import lombok.Getter;

import java.util.Arrays;

public enum ReserveType {

    STANDARD("STND");

    @Getter
    private String value;

    ReserveType(String value) {
        this.value = value;
    }

    public String getRedisKey(){
        return "reserve.number."+value.toLowerCase();
    }

    public static ReserveType ofReserveType(String type){
        return Arrays.stream(values()).filter(s -> s.value.equals(type)).findAny().orElse(null);
    }

}
