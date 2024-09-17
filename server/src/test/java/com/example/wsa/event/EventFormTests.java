package com.example.wsa.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventFormTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventFormJDBC eventFormJDBC;

    @Autowired
    private ObjectMapper objectMapper;

    private EventForm eventForm;

    @BeforeEach
    void setUp() {
        eventForm = new EventForm();
    }

    // Unit tests for EventForm class

    @Test
    public void testTitleGetterAndSetter() {
        eventForm.setTitle("Sample Event");
        assertEquals("Sample Event", eventForm.getTitle());
    }


    @Test
    public void testDateGetterAndSetter() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
        Date date = dateFormat.parse("August 27, 2024");
        eventForm.setDate(date);
        assertEquals(date, eventForm.getDate());
    }

    @Test
    public void testStartTimeGetterAndSetter() {
        eventForm.setStartTime("10:00 AM");
        assertEquals("10:00 AM", eventForm.getStartTime());
    }

    @Test
    public void testEndTimeGetterAndSetter() {
        eventForm.setEndTime("12:00 PM");
        assertEquals("12:00 PM", eventForm.getEndTime());
    }

    @Test
    public void testDescriptionGetterAndSetter() {
        eventForm.setDescription("This is a test event.");
        assertEquals("This is a test event.", eventForm.getDescription());
    }

    @Test
    public void testDbsRequiredGetterAndSetter() {
        eventForm.setDbsRequired(true);
        assertTrue(eventForm.getDbsRequired());
    }

    @Test
    public void testAccessibilityAssistanceProvidedGetterAndSetter() {
        eventForm.setAccessibilityAssistanceProvided("Wheelchair access");
        assertEquals("Wheelchair access", eventForm.getAccessibilityAssistanceProvided());
    }

    @Test
    public void testCityGetterAndSetter() {
        eventForm.setCity("Test City");
        assertEquals("Test City", eventForm.getCity());
    }

    @Test
    public void testPostalCodeGetterAndSetter() {
        eventForm.setPostalCode("12345");
        assertEquals("12345", eventForm.getPostalCode());
    }

    @Test
    public void testLandmarkGetterAndSetter() {
        eventForm.setLandmark("Near Park");
        assertEquals("Near Park", eventForm.getLandmark());
    }

    @Test
    void testConvertImageToBytes() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.png", "image/png", "sample image content".getBytes());
        eventForm.setMainImage(mockFile);
        eventForm.convertImageToBytes();

        assertNotNull(eventForm.getImage());
        assertArrayEquals("sample image content".getBytes(), eventForm.getImage());
    }

    @Test
    void testEmptyImageDoesNotConvert() throws IOException {
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.png", "image/png", new byte[0]);
        eventForm.setMainImage(mockFile);
        eventForm.convertImageToBytes();

        assertNull(eventForm.getImage());
    }

    // Integration test for EventFormController

    @Test
    void testAddEvent() throws Exception {
        // Creating a mock image file
        MockMultipartFile mockImage = new MockMultipartFile("mainImage", "event.jpg", "image/jpeg", "image content".getBytes());

        // Mock the saveEvent method to do nothing when called
        doNothing().when(eventFormJDBC).saveEvent(any(EventDTO.class));

        // Perform the POST request with the date in the formatted string
        mockMvc.perform(multipart("/api/events")
                        .file(mockImage)
                        .param("title", "Community Sports Day")
                        .param("date", "August 27, 2024")
                        .param("startTime", "10:00")
                        .param("endTime", "17:00")
                        .param("description", "A fun day for the community to come together and enjoy various sports activities.")
                        .param("address", "123 Sports Lane")
                        .param("city", "Cardiff")
                        .param("postalCode", "CF10 1AA")
                        .param("landmark", "Next to the community center")
                        .param("dbsRequired", "false")
                        .param("accessibilityAssistanceProvided", "Wheelchair access")
                        .param("rolesNeeded", "Volunteers")
                        .param("rewardsOffering", "Free lunch"))
                .andExpect(status().isOk())
                .andExpect(content().string("Event created successfully"));
    }
}
