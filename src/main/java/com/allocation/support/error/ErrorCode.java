package com.allocation.support.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {

    INVALID_PARAMETER("INVALID_PARAMETER","요청 값 또는 요청인자 형식 오류입니다."),
    ALREADY_RESERVATION("ALREADY_RESERVATION","이미 예약된 상태 입니다."),
    NOTFOUND_RESERVATION("NOTFOUND_RESERVATION","예약번호가 존재 하지 않습니다."),
    ALREADY_ALLOCATE_DRIVER("ALREADY_ALLOCATE_DRIVER","기사님을 배정할수 없습니다."),
    INVALID_RESERVATION("INVALID_RESERVATION","예약 상태 오류입니다."),
    NOTFOUND_ALLOCATE_DRIVER("NOTFOUND_ALLOCATE_DRIVER","배차된 차량이 없습니다."),
    INVALID_DRIVE_STOP("INVALID_DRIVE_STOP","운행 중단 가능한 상태가 아닙니다."),
    NOT_PERMIT_WITHDRAW("NOT_PERMIT_WITHDRAW","예약 취소를 허용하지 않습니다."),
    SYSTEM_ERROR("SYSTEM_ERROR","시스템 오류")
    ;

    @Getter
    private String code;

    @Getter
    private String msg;


}
