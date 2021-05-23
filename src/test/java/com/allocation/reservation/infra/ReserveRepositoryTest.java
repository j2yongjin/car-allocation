package com.allocation.reservation.infra;

import com.allocation.reservation.domain.Reservation;
import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.domain.ReserveStatus;
import com.allocation.reservation.domain.ReserveType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReserveRepositoryTest {

    @Autowired
    ReserveRepository reserveRepository;

    @Test
    public void whenSave_thenSuccess(){
        String departure = "출발지";
        // when
        ReserveId reserveId = ReserveId.of(ReserveType.STANDARD, "ABCD", "001");
        Reservation reservation = Reservation.builder()
                .reserveId(reserveId.getReserveId())
                .departure(departure)
                .destination("도착지")
                .startDatetime(LocalDateTime.now())
                .endDatetime(LocalDateTime.now().plusHours(2))
                .regDatetime(LocalDateTime.now())
                .requestDatetime(LocalDateTime.now())
                .status(ReserveStatus.READY)
                .requestId("reqId-001")
                .userName("yjlee")
                .userTelephone("01011112222")
                .build();

        Reservation result = reserveRepository.save(reservation);

        //then
        Reservation find = reserveRepository.findById(result.getReserveId()).get();
        assertThat(find.getDeparture() , Matchers.equalTo(departure));
        assertThat(find.getReserveId() , Matchers.equalTo(reserveId.getReserveId()));
    }

}