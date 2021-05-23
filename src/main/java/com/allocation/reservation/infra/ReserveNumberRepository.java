package com.allocation.reservation.infra;

import com.allocation.reservation.domain.ReserveType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReserveNumberRepository {

    private final RedisTemplate redisTemplate;

    public String getReserveNumber(ReserveType reserveType){
        return String.format("%12d",999999999999L-redisTemplate.opsForValue().increment(reserveType.getRedisKey()));
    }
}
