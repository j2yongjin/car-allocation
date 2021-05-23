package com.allocation.driver.infra;

import com.allocation.driver.domain.AllocateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AllocateHistoryRepository extends JpaRepository<AllocateHistory,Long> {

    @Query("select a from AllocateHistory a where a.driverTelephone = :telephone " +
            "and (:stDttm between a.reserveStDttm and a.reserveEndDttm or :endDttm between a.reserveStDttm and a.reserveEndDttm)")
    Optional<AllocateHistory> findOneByDriverTelephoneAndScheduleEqual(@Param("telephone") String telephone
            , @Param("stDttm") LocalDateTime stDttm , @Param("endDttm")LocalDateTime endDttm);

    Optional<AllocateHistory> findOneByReserveId(String reserveId);
}
