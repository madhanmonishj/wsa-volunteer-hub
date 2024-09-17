package com.example.wsa.quickRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quickregister")
public class QuickRegisterController {

    @Autowired
    private QuickRegisterService quickRegisterService;

    @PostMapping()
    public ResponseEntity<String> quickRegisterVolunteer(@RequestBody QuickRegisterDTO quickRegisterDTO) {
        return quickRegisterService.quickRegisterVolunteer(quickRegisterDTO);
    }
}
