package com.example.wsa.event;

import com.example.wsa.event.exception.EventControllerException;
import com.example.wsa.event.exception.EventException;
import com.example.wsa.event.exception.EventServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Event Controller Class
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class EventController {

    private final EventRepo eventRepo;

    @Autowired
    public EventController(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @GetMapping("/events")
    public Page<EventDTO> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return eventRepo.getAllEvents(pageable);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable int id) throws EventException {
        try {
        EventDTO eventDetails = eventRepo.getEvent(id);
        log.debug("EventController.getEvent({}) : {}", id, eventDetails);
        return ResponseEntity.ok(eventDetails);
        } catch (EventServiceException e) {
            throw new EventControllerException("Event Service Exception getEvent()", e.getMessage());
        }
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<EventDTO> updateEventInfo(@PathVariable int id, @RequestBody EventDTO event) throws EventException {
        try {
            EventDTO updateEvent = eventRepo.updateEventInfo(id, event);
            log.debug("EventController.updateEventInfo({}): Event: {}", id, event);
            return ResponseEntity.ok(updateEvent);
        } catch (EventServiceException e) {
            log.error("EventController.updateEventInfo({})", id, e);
            throw new EventControllerException("Event Service Exception updateEventInfo()", e.getMessage());
        }
    }

    @PostMapping("/events")
    public ResponseEntity<EventDTO> saveEvent(@ModelAttribute EventDTO event) throws EventException {
        EventDTO updateEvent = eventRepo.saveEvent(event);
        log.debug("EventController.updateEvent({}):",updateEvent);
        return ResponseEntity.ok(updateEvent);
    }

    @GetMapping("/event-details/{id}")
    public ResponseEntity<EventDTO> getEventDetails(@PathVariable int id) throws EventException {
        EventDTO eventDetails = eventRepo.getEventDetails(id);
        log.debug("EventController.getEventDetails({}): Event: {}", id, eventDetails);
        return ResponseEntity.ok(eventDetails);
    }
}
