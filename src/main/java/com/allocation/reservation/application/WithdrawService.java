package com.allocation.reservation.application;

import com.allocation.reservation.model.ReserveWithdrawRequest;

public interface WithdrawService {

    void withdraw(ReserveWithdrawRequest request);
}
