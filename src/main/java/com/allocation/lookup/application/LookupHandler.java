package com.allocation.lookup.application;

import com.allocation.lookup.infra.ReservationLookupRepository;
import com.allocation.lookup.model.ReservationsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LookupHandler {

    private final ReservationLookupRepository reservationLookupRepository;

    public ReservationsResponse getReservations(){
        return ReservationsResponse.of(reservationLookupRepository.findAll());
    }
}
