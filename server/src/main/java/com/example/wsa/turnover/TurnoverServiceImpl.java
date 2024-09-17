package com.example.wsa.turnover;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TurnoverServiceImpl implements TurnoverService {

    private final TurnoverRepository turnoverRepository;

    @Autowired
    public TurnoverServiceImpl(TurnoverRepository turnoverRepository) {
        this.turnoverRepository = turnoverRepository;
    }

    @Override
    public List<Turnover> getAllTurnovers() {
        log.debug("TurnoverServiceImpl.getAllTurnovers() : {}",turnoverRepository.findAll());
        return turnoverRepository.findAll();
    }
}