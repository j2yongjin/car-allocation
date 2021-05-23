package com.allocation.reservation.infra;

import com.allocation.reservation.domain.Reservation;
import com.allocation.reservation.domain.ReserveId;
import com.allocation.reservation.domain.ReserveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReserveRepository extends JpaRepository<Reservation, String> {
//    Optional<Reservation> findOneByStartDatetimeAfterAndEndDatetimeBeforeAndStatus(LocalDateTime start, LocalDateTime end, ReserveStatus status);

    @Query("select a from Reservation a where a.status=:status and ( :start between a.startDatetime and a.endDatetime  or :end between  a.startDatetime and a.endDatetime)")
    Optional<Reservation> findOneByReservationScheduleEqual(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end, @Param("status")ReserveStatus status);

}
