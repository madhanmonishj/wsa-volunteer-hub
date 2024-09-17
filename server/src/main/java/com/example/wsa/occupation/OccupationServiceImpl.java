package com.example.wsa.occupation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class OccupationServiceImpl implements OccupationService{
    private final OccupationRepository occupationRepository;

    @Autowired
    public OccupationServiceImpl(OccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }

    @Override
    public List<Occupation> getAllOccupations() {
        log.debug("OccupationServiceImpl.getAllOccupations() : {}",occupationRepository.findAll());
        return occupationRepository.findAll();
    }

}
