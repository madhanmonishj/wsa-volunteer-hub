package com.example.wsa.occupation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/occupations")
@Slf4j
public class OccupationController {
    private final OccupationService occupationService;

    @Autowired
    public OccupationController(OccupationService occupationService) {
        this.occupationService = occupationService;
    }

    @GetMapping
    public List<Occupation> getAllOccupations() {
        log.debug("OccupationController.getAllOccupations()");
        return occupationService.getAllOccupations();
    }
}
