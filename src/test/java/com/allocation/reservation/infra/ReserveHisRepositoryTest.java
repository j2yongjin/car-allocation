package com.allocation.reservation.infra;

import com.allocation.reservation.domain.ReservationHistory;
import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.domain.ReserveStatus;
import com.allocation.reservation.domain.ReserveType;
import com.allocation.support.util.RandomUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReserveHisRepositoryTest {

    @Autowired
    ReserveHisRepository reserveHisRepository;

    @Autowired
    ReserveNumberRepository numberRepository;

    @Test
    public void whenSave_thenSuccess(){

        // given
        ReserveId reserveId = ReserveId.of(ReserveType.STANDARD, RandomUtil.getRandomString(12),numberRepository.getReserveNumber(ReserveType.STANDARD));

        // when
        ReservationHistory history = ReservationHistory.builder()
                .reserveId(reserveId.getReserveId())
                .regDate(LocalDateTime.now())
                .status(ReserveStatus.READY)
                .build();

        ReservationHistory result = reserveHisRepository.save(history);

        ReservationHistory finded = reserveHisRepository.findById(result.getReservationHisNo()).get();

        System.out.println(" no : " + finded.getReservationHisNo() + " , status : " + finded.getStatus());

    }

}