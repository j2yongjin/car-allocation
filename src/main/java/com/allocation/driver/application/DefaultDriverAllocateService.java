package com.allocation.driver.application;

import com.allocation.application.InternalException;
import com.allocation.application.ServiceException;
import com.allocation.driver.infra.AllocateHistoryRepository;
import com.allocation.driver.model.DriverAllocateRequest;
import com.allocation.reservation.domain.Reservation;
import com.allocation.reservation.domain.ReservationHistory;
import com.allocation.reservation.domain.ReserveStatus;
import com.allocation.reservation.infra.ReserveHisRepository;
import com.allocation.reservation.infra.ReserveRepository;
import com.allocation.support.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultDriverAllocateService implements DriverAllocateService{

    private final AllocateHistoryRepository historyRepository;
    private final ReserveRepository reserveRepository;
    private final ReserveHisRepository reserveHisRepository;

    @Override
    @Transactional
    public void allocate(DriverAllocateRequest request) {

        try {
            Reservation reservation = reserveRepository.findById(request.getReservation().getIdentifier())
                    .orElseThrow(() -> new ServiceException(ErrorCode.NOTFOUND_RESERVATION));

            validate(request,reservation);

            historyRepository.save(request.getAllocateHistory(reservation.getReserveId(),reservation.getStartDatetime() , reservation.getEndDatetime()));

            reservation.allocate();
            reserveRepository.save(reservation);

            reserveHisRepository.save(new ReservationHistory(reservation.getReserveId(),ReserveStatus.ALLOCATED));

        }catch (ServiceException de){
            throw de;
        }catch (Exception e){
            throw new InternalException(ErrorCode.SYSTEM_ERROR,e.getMessage(),e);
        }
    }

    private void validate(DriverAllocateRequest request,Reservation reservation){
        if(!reservation.isAllocatable()) throw new ServiceException(ErrorCode.ALREADY_ALLOCATE_DRIVER,"이미 기사님이 배정되었습니다.");

        historyRepository.findOneByDriverTelephoneAndScheduleEqual(request.getDriver().getTelephone()
        ,reservation.getStartDatetime(),reservation.getEndDatetime())
        .ifPresent(s -> new ServiceException(ErrorCode.ALREADY_ALLOCATE_DRIVER," 기사님 에게 이미 배정된 시간이 있습니다."));
    }




}
