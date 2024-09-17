package com.example.wsa.turnover;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turnover")
@Slf4j
public class TurnoverController {

    private final TurnoverService turnoverService;

    @Autowired
    public TurnoverController(TurnoverService turnoverService) {
        this.turnoverService = turnoverService;
    }

    @GetMapping
    public List<Turnover> getAllTurnovers() {
        log.debug("TurnoverController.getAllTurnovers()");
        return turnoverService.getAllTurnovers();
    }
}
