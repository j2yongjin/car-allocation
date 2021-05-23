package com.allocation.driver.application;

import com.allocation.application.InternalException;
import com.allocation.application.ServiceException;
import com.allocation.driver.domain.AllocateHistory;
import com.allocation.driver.infra.AllocateHistoryRepository;
import com.allocation.driver.model.DriverStopRequest;
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
public class DefaultDriverStopService implements DriverStopService{

    private final AllocateHistoryRepository historyRepository;
    private final ReserveRepository reserveRepository;
    private final ReserveHisRepository reserveHisRepository;

    @Override
    @Transactional
    public void stop(DriverStopRequest request) {
        try {
            reservationComplete(request);
            driverStop(request);
        }catch (ServiceException ex){
            throw ex;
        }catch (Exception e){
            throw new InternalException(ErrorCode.SYSTEM_ERROR);
        }

    }

    private void reservationComplete(DriverStopRequest request) {
        Reservation reservation = reserveRepository.findById(request.getReservation().getIdentifier()).orElseThrow(
                () -> new ServiceException(ErrorCode.NOTFOUND_RESERVATION)
        );

        reservation.complete();
        reserveRepository.save(reservation);

        ReservationHistory history = ReservationHistory.builder().status(ReserveStatus.DRIVING).reserveId(request.getReservation().getIdentifier()).regDate(LocalDateTime.now()).build();
        reserveHisRepository.save(history);
    }

    private void driverStop(DriverStopRequest request) {
        AllocateHistory allocateHistory = historyRepository.findOneByReserveId(request.getReservation().getIdentifier())
                .orElseThrow(() -> new ServiceException(ErrorCode.NOTFOUND_ALLOCATE_DRIVER));

        if (!allocateHistory.isStoppable())
            throw new ServiceException(ErrorCode.INVALID_DRIVE_STOP, "운행중에만 운행중지 가능합니다.");

        allocateHistory.complete(request.getEndDatetime());

        historyRepository.save(allocateHistory);
    }
}
