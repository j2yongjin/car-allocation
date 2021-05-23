package com.allocation.reservation.application;

import com.allocation.application.InternalException;
import com.allocation.application.ServiceException;
import com.allocation.driver.domain.AllocateHistory;
import com.allocation.driver.infra.AllocateHistoryRepository;
import com.allocation.reservation.domain.Reservation;
import com.allocation.reservation.domain.ReservationHistory;
import com.allocation.reservation.domain.ReserveStatus;
import com.allocation.reservation.infra.ReserveHisRepository;
import com.allocation.reservation.infra.ReserveRepository;
import com.allocation.reservation.model.ReserveWithdrawRequest;
import com.allocation.support.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultWithdrawService implements WithdrawService{

    private final AllocateHistoryRepository historyRepository;
    private final ReserveRepository reserveRepository;
    private final ReserveHisRepository reserveHisRepository;

    // TODO: 23/05/2021 환불 정책 관리 도메인으로 변경 필요함
    private final Integer WITHDRAW_PERMITTED_TIME = 1;

    @Override
    public void withdraw(ReserveWithdrawRequest request) {

        try {
            Reservation reservation = reserveRepository.findById(request.getReservation().getIdentifier()).orElseThrow(
                    () -> new ServiceException(ErrorCode.NOTFOUND_RESERVATION)
            );

            validate(reservation,request);

            reservationWithdraw(request, reservation);

            driverWithdraw(request);

        }catch (ServiceException ex){
            throw ex;
        }catch (Exception e){
            throw new InternalException(ErrorCode.SYSTEM_ERROR);
        }
    }

    private void validate(Reservation reservation,ReserveWithdrawRequest request){
        LocalDateTime withdrawPermittedTime = LocalDateTime.now().plusHours(WITHDRAW_PERMITTED_TIME);
        if(!reservation.isWithdrawable(request.getMember().getName(),request.getMember().getTelephone(),withdrawPermittedTime))
            throw new ServiceException(ErrorCode.NOT_PERMIT_WITHDRAW);
    }

    private void reservationWithdraw(ReserveWithdrawRequest request, Reservation reservation) {
        reservation.withdraw();
        reserveRepository.save(reservation);

        ReservationHistory history = ReservationHistory.builder().status(ReserveStatus.WITHDRAW).reserveId(request.getReservation().getIdentifier()).regDate(LocalDateTime.now()).build();
        reserveHisRepository.save(history);
    }

    private void driverWithdraw(ReserveWithdrawRequest request) {
        Optional<AllocateHistory> allocateHistory = historyRepository.findOneByReserveId(request.getReservation().getIdentifier());

        if(allocateHistory.isPresent()) {
            allocateHistory.get().withdraw();
            historyRepository.save(allocateHistory.get());
        }
    }

}
