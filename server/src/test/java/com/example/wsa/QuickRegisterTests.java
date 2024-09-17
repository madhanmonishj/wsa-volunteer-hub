package com.example.wsa;

import com.example.wsa.quickRegister.QuickRegisterDTO;
import com.example.wsa.quickRegister.QuickRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * Test Case for inserting data in temporary_volunteers table
 */
@SpringBootTest
@AutoConfigureMockMvc
public class QuickRegisterTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuickRegisterService quickRegisterService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterVolunteer() throws Exception {
        QuickRegisterDTO quickRegisterData = new QuickRegisterDTO(
                1,
                "Test",
                "WSA",
                "testwsa@gmail.com",
                "7470442377"
        );

        when(quickRegisterService.quickRegisterVolunteer(any(QuickRegisterDTO.class)))
                .thenReturn(ResponseEntity.ok("Registration successful"));

        mockMvc.perform(post("/api/quickregister")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(quickRegisterData)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registration successful"));
    }
}
