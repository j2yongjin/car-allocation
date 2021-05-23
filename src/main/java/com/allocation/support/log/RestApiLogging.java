package com.allocation.support.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class RestApiLogging {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restApis(){}

    @Around(value = "restApis()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Object result;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try{
            log.info(" Request API : {} , Param : {} " ,request.getRequestURL() ,getArgs(joinPoint.getArgs()));
            result = joinPoint.proceed();
            stopWatch.stop();
            log.info(" Response API : {} , ElapsedTime : {} , Response : {} " , request.getRequestURL() , stopWatch.getTotalTimeMillis() , result != null ? result.toString() : null);

        }catch (Throwable e){
            if(stopWatch.isRunning()) stopWatch.stop();

            log.error(" Error API : {} ElapsedTime : {} , Param : {} " ,request.getRequestURL(),stopWatch.getTotalTimeMillis(),getArgs(joinPoint.getArgs()));
            throw e;
        }finally {
            if(stopWatch.isRunning())
                stopWatch.stop();
        }
        return result;
    }

    private String getArgs(Object[] args){
        if(args.length==0) return "";
        return Arrays.stream(args).map(s -> s.toString()).collect(Collectors.joining(" , "));
    }

}
