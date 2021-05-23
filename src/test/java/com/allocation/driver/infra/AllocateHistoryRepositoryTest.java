package com.allocation.driver.infra;

import com.allocation.driver.domain.AllocateHistory;
import com.allocation.reservation.domain.ReserveStatus;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AllocateHistoryRepositoryTest {

    @Autowired
    AllocateHistoryRepository allocateHistoryRepository;

    @Test
    public void 중복배정기사(){

        // given
        String driverTelephone = "01011112222";
        LocalDateTime reserveStDttm = LocalDateTime.now().minusMinutes(10);
        LocalDateTime reserveEndDttm = LocalDateTime.now().plusMinutes(10);
        AllocateHistory allocateHistory = AllocateHistory.builder()
                .reserveStDttm(reserveStDttm)
                .reserveEndDttm(reserveEndDttm)
                .status(ReserveStatus.ALLOCATED)
                .driverTelephone(driverTelephone)
                .regDatetime(LocalDateTime.now())
                .carId("12가1234")
                .driverName("yjlee")
                .reserveId("STNDOGKLSYALHKOW999999999997")
                .requestDatetime(LocalDateTime.now())
                .requestId("req-001")
                .endDatetime(LocalDateTime.now())
                .build();
        allocateHistoryRepository.save(allocateHistory);

        // when
        allocateHistoryRepository.findOneByDriverTelephoneAndScheduleEqual(driverTelephone,reserveStDttm.minusMinutes(23),reserveEndDttm.minusMinutes(19)).get();

    }

}