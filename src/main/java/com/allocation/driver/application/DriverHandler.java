package com.allocation.driver.application;

import com.allocation.driver.model.DriverAllocateRequest;
import com.allocation.driver.model.DriverStartRequest;
import com.allocation.driver.model.DriverStopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverHandler {

    private final DriverAllocateService allocateService;
    private final DriverStartService driverStartService;
    private final DriverStopService driverStopService;

    public void allocate(DriverAllocateRequest request){
        allocateService.allocate(request);
    }

    public void start(DriverStartRequest request){
        driverStartService.start(request);
    }

    public void stop(DriverStopRequest request){
        driverStopService.stop(request);
    }
}
