package com.allocation.reservation.application;

import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.domain.ReserveType;
import com.allocation.reservation.infra.ReserveNumberRepository;
import com.allocation.support.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReserveIdGenerator {

    private final ReserveNumberRepository reserveNumberRepository;

    public ReserveId getReserveId(ReserveType reserveType){
        return ReserveId.of(reserveType, RandomUtil.getRandomString(12),reserveNumberRepository.getReserveNumber(reserveType));
    }
}
