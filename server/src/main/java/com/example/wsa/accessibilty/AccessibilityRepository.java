package com.example.wsa.accessibilty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessibilityRepository extends JpaRepository<Accessibility, Integer> {
}