package com.allocation.driver.application;

import com.allocation.application.InternalException;
import com.allocation.application.ServiceException;
import com.allocation.driver.domain.AllocateHistory;
import com.allocation.driver.infra.AllocateHistoryRepository;
import com.allocation.driver.model.DriverStartRequest;
import com.allocation.reservation.domain.Reservation;
import com.allocation.reservation.domain.ReservationHistory;
import com.allocation.reservation.domain.ReserveStatus;
import com.allocation.reservation.infra.ReserveHisRepository;
import com.allocation.reservation.infra.ReserveRepository;
import com.allocation.support.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DefaultDriverStartService implements DriverStartService{

    private final AllocateHistoryRepository historyRepository;
    private final ReserveRepository reserveRepository;
    private final ReserveHisRepository reserveHisRepository;

    @Override
    @Transactional
    public void start(DriverStartRequest request) {
        try {
            reservation(request);
            driverStart(request);
        }catch (ServiceException se){
            throw se;
        }catch (Exception e){
            throw new InternalException(ErrorCode.SYSTEM_ERROR);
        }
    }

    private void driverStart(DriverStartRequest request) {
        AllocateHistory allocateHistory = historyRepository.findOneByReserveId(request.getReservation().getIdentifier()).orElseThrow(
                () -> new ServiceException(ErrorCode.NOTFOUND_ALLOCATE_DRIVER)
        );

        if (!allocateHistory.isStartable())
            throw new ServiceException(ErrorCode.INVALID_RESERVATION, "배차 상태에서만 운행 가능합니다.");

        allocateHistory.start(request.getStartDatetime());
        historyRepository.save(allocateHistory);
    }

    private void reservation(DriverStartRequest request) {
        Reservation reservation = reserveRepository.findById(request.getReservation().getIdentifier()).orElseThrow(
                () -> new ServiceException(ErrorCode.NOTFOUND_RESERVATION)
        );

        reservation.start();
        reserveRepository.save(reservation);

        ReservationHistory history = ReservationHistory.builder().status(ReserveStatus.DRIVING).reserveId(request.getReservation().getIdentifier()).regDate(LocalDateTime.now()).build();
        reserveHisRepository.save(history);
    }

}
