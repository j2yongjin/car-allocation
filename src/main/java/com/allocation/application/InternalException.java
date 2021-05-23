package com.allocation.application;

import com.allocation.support.error.ErrorCode;

public class InternalException extends RuntimeException{
    private ErrorCode errorCode;

    public InternalException(ErrorCode errorCode , String message , Throwable throwable){
        super(message,throwable);
        this.errorCode = errorCode;
    }

    public InternalException(ErrorCode errorCode , String message){
        super(message);
        this.errorCode = errorCode;
    }

    public InternalException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
