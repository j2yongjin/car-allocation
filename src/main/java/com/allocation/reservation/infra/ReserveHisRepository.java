package com.allocation.reservation.infra;

import com.allocation.reservation.domain.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveHisRepository extends JpaRepository<ReservationHistory , Long> {
}
