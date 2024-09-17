package com.example.wsa.event;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventDTOTest {

    @Test
    void testEventDTO() {
        EventDTO event = new EventDTO();
        event.setId(1);
        event.setName("Test Event");
        event.setDescription("Test Description");

        assertEquals(1, event.getId());
        assertEquals("Test Event", event.getName());
        assertEquals("Test Description", event.getDescription());
    }

}
