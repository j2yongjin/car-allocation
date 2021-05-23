package com.allocation.application;

import com.allocation.support.error.ErrorCode;

public class ServiceException extends RuntimeException{

    private ErrorCode errorCode;

    public ServiceException(ErrorCode errorCode , String message , Throwable throwable){
        super(message,throwable);
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode , String message){
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
