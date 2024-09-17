package com.example.wsa.accessibilty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccessibilityServiceImpl implements AccessibilityService {
    private final AccessibilityRepository accessibilityRepository;

    @Autowired
    public AccessibilityServiceImpl(AccessibilityRepository accessibilityRepository) {
        this.accessibilityRepository = accessibilityRepository;
    }

    @Override
    public List<Accessibility> getAllAccessibilities() {
        log.debug("AccessibilityServiceImpl.getAllAccessibility() : {}",accessibilityRepository.findAll());
        return accessibilityRepository.findAll();
    }
}
