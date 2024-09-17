package com.example.wsa.quickRegister;

import org.springframework.http.ResponseEntity;

public interface QuickRegisterService {
    ResponseEntity<String> quickRegisterVolunteer(QuickRegisterDTO quickRegisterDTO);
}
