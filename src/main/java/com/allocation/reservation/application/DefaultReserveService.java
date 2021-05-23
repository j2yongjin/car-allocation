package com.allocation.reservation.application;

import com.allocation.application.InternalException;
import com.allocation.application.ServiceException;
import com.allocation.reservation.domain.*;
import com.allocation.reservation.infra.ReserveHisRepository;
import com.allocation.reservation.infra.ReserveRepository;
import com.allocation.reservation.model.ReserveRequest;
import com.allocation.support.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DefaultReserveService implements ReserveService {

    private final ReserveIdGenerator idGenerator;
    private final ReserveRepository reserveRepository;
    private final ReserveHisRepository reserveHisRepository;

    @Transactional
    public ReserveId reserve(ReserveRequest request){

        try {
            validate(request);

            ReserveId reserveId = idGenerator.getReserveId(ReserveType.STANDARD);
            Reservation reservation = request.getReservation(reserveId, ReserveStatus.READY);

            createReservation(reserveId, reservation);
            return reserveId;
        }catch (ServiceException rse){
            throw rse;
        }catch (Exception e){
            throw new InternalException(ErrorCode.SYSTEM_ERROR,e.getMessage(),e);
        }
    }

    private void createReservation(ReserveId reserveId, Reservation reservation) {
        reserveRepository.save(reservation);

        ReservationHistory history = ReservationHistory.builder().status(ReserveStatus.READY).reserveId(reserveId.getReserveId()).regDate(LocalDateTime.now()).build();
        reserveHisRepository.save(history);
    }

    private void validate(ReserveRequest request){
        LocalDateTime startDatetime = LocalDateTime.of(request.getTime().getStartDate(),request.getTime().getStartTime());
        LocalDateTime endDatetime = startDatetime.minusMinutes(request.getTime().getEstimatedTime());
        reserveRepository.findOneByReservationScheduleEqual(startDatetime,endDatetime,ReserveStatus.READY)
                .ifPresent(( a ) -> new ServiceException(ErrorCode.ALREADY_RESERVATION," 예약 취소 후 이용해 주세요"));
    }


}
