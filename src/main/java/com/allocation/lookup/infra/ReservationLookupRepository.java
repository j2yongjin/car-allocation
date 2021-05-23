package com.allocation.lookup.infra;

import com.allocation.lookup.domain.ReservationDetail;

import java.util.List;

public interface ReservationLookupRepository {

    List<ReservationDetail> findAll();

}
