package com.example.wsa.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

/**
 * EventFormController class to handle HTTP requests related to event forms.
 * This class provides endpoints to create, update, and retrieve event data.
 */
@RestController
@RequestMapping("/api")
@Validated
public class EventFormController {

    /**
     * The EventFormJDBC instance used to interact with the database.
     */
    @Autowired
    private EventFormJDBC eventFormJDBC;

    /**
     * Converts an EventForm object to an EventDTO object.
     *
     * @param eventForm the EventForm object to be converted
     * @return the converted EventDTO object
     */
    private EventDTO convertToDTO(EventForm eventForm) {
        EventDTO eventDTO = new EventDTO();

        // Set basic fields
        eventDTO.setName(eventForm.getTitle());
        eventDTO.setDescription(eventForm.getDescription());
        eventDTO.setAddress(eventForm.getAddress());
        eventDTO.setCity(eventForm.getCity());
        eventDTO.setPostalCode(eventForm.getPostalCode());
        eventDTO.setLandmark(eventForm.getLandmark());
        eventDTO.setDbsRequired(eventForm.getDbsRequired());

        eventDTO.setAccessibilityAssistance(eventForm.getAccessibilityAssistanceProvided().isEmpty() ? Arrays.asList() : Arrays.asList(eventForm.getAccessibilityAssistanceProvided().split(",")));
        eventDTO.setRolesNeeded(eventForm.getRolesNeeded().isEmpty() ? Arrays.asList() : Arrays.asList(eventForm.getRolesNeeded().split(",")));

        eventDTO.setRewardsOffering(eventForm.getRewardsOffering());

        // Handle the date and time components
        if (eventForm.getDate() != null) {
            eventDTO.setDate(eventForm.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            if (eventForm.getStartTime() != null && !eventForm.getStartTime().isEmpty()) {
                eventDTO.setStartTime(
                        LocalDateTime.of(eventDTO.getDate(), LocalDateTime.parse("1970-01-01T" + eventForm.getStartTime()).toLocalTime())
                );
            }

            if (eventForm.getEndTime() != null && !eventForm.getEndTime().isEmpty()) {
                eventDTO.setEndTime(
                        LocalDateTime.of(eventDTO.getDate(), LocalDateTime.parse("1970-01-01T" + eventForm.getEndTime()).toLocalTime())
                );
            }
        }

        // Handle the image if present
        if (eventForm.getImage() != null) {
            eventDTO.setEventImage(eventForm.getImage());
        }

        return eventDTO;
    }

//    /**
//     * Endpoint to handle POST requests for creating events.
//     *
//     * @param eventForm the EventForm object containing the event data
//     * @return a ResponseEntity with a success or error message
//     */
//    @PostMapping("/events")
////    public ResponseEntity<String> createEvent(@Valid @ModelAttribute EventForm eventForm) {
////        try {
////            eventForm.convertImageToBytes(); // Convert image to byte array
////            EventDTO eventDTO = convertToDTO(eventForm); // Convert EventForm to EventDTO
////            eventFormJDBC.saveEvent(eventDTO); // Save event data to the database
////
////            return new ResponseEntity<>("Event created successfully", HttpStatus.OK);
////        } catch (Exception e) {
////            e.printStackTrace();
////            return new ResponseEntity<>("Error creating event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }



    /**
     * Endpoint to handle PUT requests for updating existing events.
     *
     * @param id the ID of the event to be updated
     * @param eventForm the EventForm object containing the updated event data
     * @return a ResponseEntity with a success or error message
     */
    @PutMapping("/events/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable int id, @Valid @ModelAttribute EventForm eventForm) {
        try {
            eventForm.convertImageToBytes(); // Convert image to byte array
            EventDTO eventDTO = convertToDTO(eventForm); // Convert EventForm to EventDTO
            eventFormJDBC.updateEvent(id, eventDTO); // Update event data in the database

            return new ResponseEntity<>("Event updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error updating event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint to handle GET requests for fetching an event by its ID.
     *
     * @param id the ID of the event to retrieve
     * @return a ResponseEntity containing the EventDTO if found, or a NOT_FOUND status if not found
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable int id) {
        Optional<EventDTO> eventDTO = eventFormJDBC.getEventById(id);
        return eventDTO.map(event -> new ResponseEntity<>(event, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
