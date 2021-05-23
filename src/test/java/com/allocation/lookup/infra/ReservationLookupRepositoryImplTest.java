package com.allocation.lookup.infra;

import com.allocation.lookup.domain.ReservationDetail;
import com.allocation.reservation.domain.Reservation;
import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.domain.ReserveStatus;
import com.allocation.reservation.domain.ReserveType;
import com.allocation.reservation.infra.ReserveRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationLookupRepositoryImplTest {

    @Autowired
    ReservationLookupRepository reservationLookupRepository;

    @Autowired
    ReserveRepository reserveRepository;

    @Test
    public void givenReservation_whenFindAll_thenSuccess(){

        // given
        String departure = "출발지";
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
        reserveRepository.save(reservation);

        // when
        List<ReservationDetail> reservationDetails = reservationLookupRepository.findAll();

        // then
        assertThat(reservationDetails.size(), Matchers.equalTo(1));

    }

    @Test
    public void givenReservationReady_whenIsWithdrawable_thenTrue(){

        String name = "hong";
        String telephone = "01011112222";
        Reservation reservation = Reservation.builder()
                .userName(name)
                .userTelephone(telephone)
                .status(ReserveStatus.READY)
                .build();

        // when
        Boolean result = reservation.isWithdrawable(name,telephone,LocalDateTime.now().plusHours(1));

        // then
        assertThat(result,Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void givenAllocated_whenIsWithdrawable_thenTrue(){

        String name = "hong";
        String telephone = "01011112222";
        Reservation reservation = Reservation.builder()
                .userName(name)
                .userTelephone(telephone)
                .modifyDatetime(LocalDateTime.now())
                .status(ReserveStatus.ALLOCATED)
                .build();

        LocalDateTime withdrawPermittedTime = LocalDateTime.now().plusHours(1);
        // when
        Boolean result = reservation.isWithdrawable(name,telephone,withdrawPermittedTime);

        // then
        assertThat(result,Matchers.equalTo(Boolean.TRUE));
    }

    @Test
    public void givenAllocated_whenIsWithdrawable_thenFalse(){

        String name = "hong";
        String telephone = "01011112222";
        Reservation reservation = Reservation.builder()
                .userName(name)
                .userTelephone(telephone)
                .modifyDatetime(LocalDateTime.now().plusHours(2))
                .status(ReserveStatus.ALLOCATED)
                .build();

        LocalDateTime withdrawPermittedTime = LocalDateTime.now().plusHours(1);
        // when
        Boolean result = reservation.isWithdrawable(name,telephone,withdrawPermittedTime);

        // then
        assertThat(result,Matchers.equalTo(Boolean.FALSE));
    }

}