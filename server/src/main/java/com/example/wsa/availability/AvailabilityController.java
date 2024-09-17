package com.example.wsa.availability;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/availability")
@Slf4j
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping
    public List<Availability> getAllAvailabilities() {
        log.debug("AvailabilityController::getAllAvailabilities()");
        return availabilityService.getAllAvailabilities();
    }
}