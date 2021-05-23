package com.allocation.driver.endpoint;

import com.allocation.driver.application.DriverHandler;
import com.allocation.driver.model.DriverAllocateRequest;
import com.allocation.driver.model.DriverStartRequest;
import com.allocation.driver.model.DriverStopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/car-allocation/v1/driver")
@RequiredArgsConstructor
public class DriverAllocateController {

    private final DriverHandler driverHandler;

    @PostMapping("/allocate")
    public ResponseEntity allocate(@RequestBody DriverAllocateRequest request){
        request.validate();
        driverHandler.allocate(request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/start")
    public ResponseEntity start(@RequestBody DriverStartRequest request){
        request.validate();
        driverHandler.start(request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/stop")
    public ResponseEntity stop(@RequestBody DriverStopRequest request){
        request.validate();
        driverHandler.stop(request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
