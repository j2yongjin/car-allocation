package com.allocation.reservation.infra;

import com.allocation.reservation.domain.ReserveType;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReserveNumberRepositoryTest {

    @Autowired
    ReserveNumberRepository reserveNumberRepository;

    @Test
    public void whenIncrement_thenSuccess(){
        String reserveNumber = reserveNumberRepository.getReserveNumber(ReserveType.STANDARD);
        assertThat(reserveNumber, Matchers.not(Matchers.isEmptyOrNullString()));
        assertThat(reserveNumber.length(),Matchers.equalTo(12));
    }

}