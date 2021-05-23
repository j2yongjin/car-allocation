package com.allocation.reservation.application;

import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.model.ReserveRequest;

public interface ReserveService {

    ReserveId reserve(ReserveRequest request);
}
