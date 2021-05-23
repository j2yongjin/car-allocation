package com.allocation.lookup.endpoint;

import com.allocation.lookup.application.LookupHandler;
import com.allocation.lookup.model.ReservationsResponse;
import jdk.nashorn.internal.lookup.Lookup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/car-allocation/v1/lookup")
@RequiredArgsConstructor
public class ReservationLookupController {

    private final LookupHandler lookupHandler;

    @GetMapping("/reservations")
    public ResponseEntity<ReservationsResponse> lookup(){
        return new ResponseEntity<>(lookupHandler.getReservations(), HttpStatus.OK);
    }
}
