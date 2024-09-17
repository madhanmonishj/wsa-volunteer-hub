package com.example.wsa.accessibilty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accessibility")
@Slf4j
public class AccessibilityController {

    private final AccessibilityService accessibilityService;

    @Autowired
    public AccessibilityController(AccessibilityService accessibilityService) {
        this.accessibilityService = accessibilityService;
    }

    @GetMapping
    public List<Accessibility> getAllAccessibility() {
        log.debug("AccessibilityController.getAllAccessibility()");
        return accessibilityService.getAllAccessibilities();
    }
}