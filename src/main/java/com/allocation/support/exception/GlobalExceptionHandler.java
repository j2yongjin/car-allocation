package com.allocation.support.exception;

import com.allocation.application.InternalException;
import com.allocation.application.ServiceException;
import com.allocation.support.error.Error;
import com.allocation.support.error.ErrorCode;
import com.allocation.support.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ServiceException.class })
    public ResponseEntity<ErrorResponse> handleReserve(HttpServletRequest request , ServiceException e){
        log.error(" handleReserve by URL : {} " , request.getRequestURI() ,e);
        Error error = new Error(HttpStatus.BAD_REQUEST.value()
                ,HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getErrorCode().getCode()
                ,e.getErrorCode().getMsg() + (e.getMessage() == null ? "" : "( " + e.getMessage() + " )") ,request.getRequestURI());
        return new ResponseEntity<>(new ErrorResponse(error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InternalException.class})
    public ResponseEntity<ErrorResponse> handleInternalError(HttpServletRequest request , InternalException e){
        log.error(" handleReserve by URL : {} " , request.getRequestURI() ,e);
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value()
                ,HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getErrorCode().getCode()
                ,e.getErrorCode().getMsg()  ,request.getRequestURI());
        return new ResponseEntity<>(new ErrorResponse(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(HttpServletRequest request ,MethodArgumentNotValidException e) {
        log.error(" methodArgumentNotValidException by URL : {} " , request.getRequestURI() ,e);
        Error error = new Error(HttpStatus.BAD_REQUEST.value()
                ,HttpStatus.BAD_REQUEST.getReasonPhrase(), ErrorCode.INVALID_PARAMETER.getCode()
                ,ErrorCode.INVALID_PARAMETER.getMsg() ,request.getRequestURI());

        return new ResponseEntity<>(new ErrorResponse(error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(HttpServletRequest request, HttpSession session , Throwable throwable){

        try{
            log.error(" handleThrowable by URL : {} " , request.getRequestURI() , throwable);

            Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value()
                    ,HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                    ,HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() ,request.getRequestURI());

            return new ResponseEntity<>(new ErrorResponse(error),HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception ex){
            log.error("" , ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
