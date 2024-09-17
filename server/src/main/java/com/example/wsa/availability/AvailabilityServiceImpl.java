package com.example.wsa.availability;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    @Autowired
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public List<Availability> getAllAvailabilities() {
        log.debug("AvailabilityServiceImpl::getAllAvailabilities() : {}",availabilityRepository.findAll());
        return availabilityRepository.findAll();
    }
}