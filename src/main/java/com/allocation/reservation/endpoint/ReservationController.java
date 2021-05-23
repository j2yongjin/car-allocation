package com.allocation.reservation.endpoint;

import com.allocation.reservation.application.ReservationHandler;
import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.domain.ReserveType;
import com.allocation.reservation.model.ReserveRequest;
import com.allocation.reservation.model.ReserveResponse;
import com.allocation.reservation.model.ReserveWithdrawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/car-allocation/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationHandler reservationHandler;

    @PostMapping("/reserve")
    public ResponseEntity<ReserveResponse> reserve(@RequestBody ReserveRequest request){
        request.validate();
        return new ResponseEntity<>(reservationHandler.reserve(request),HttpStatus.OK);
    }

    @PutMapping("/withdraw")
    public ResponseEntity withdraw(@RequestBody ReserveWithdrawRequest request){
        request.validate();
        reservationHandler.withdraw(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
