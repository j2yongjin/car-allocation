package com.allocation.reservation.model;

import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.domain.ReserveStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class ReserveResponse {

    private String requestId;
    private Reservation reservation;

    ReserveResponse(ReserveId reserveId, String requestId) {
        this.reservation = new Reservation(reserveId);
        this.requestId = requestId;
    }

    public static ReserveResponse of(ReserveId reserveId, String requestId){
        return new ReserveResponse(reserveId,requestId);
    }

    @ToString
    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    public static class Reservation {
        private ReserveId identifier;
    }
}
