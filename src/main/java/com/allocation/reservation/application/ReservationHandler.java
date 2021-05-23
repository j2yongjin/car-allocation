package com.allocation.reservation.application;

import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.model.ReserveRequest;
import com.allocation.reservation.model.ReserveResponse;
import com.allocation.reservation.model.ReserveWithdrawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationHandler {

    private final ReserveService reserveService;
    private final WithdrawService withdrawService;

    public ReserveResponse reserve(ReserveRequest request){
        ReserveId reserveId = reserveService.reserve(request);
        return ReserveResponse.of(reserveId,request.getRequestId());
    }

    public void withdraw(ReserveWithdrawRequest request){
        withdrawService.withdraw(request);
    }
}
