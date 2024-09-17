package com.example.wsa.volunteer;

import com.example.wsa.event.EventDTO;
import com.example.wsa.utility.NotNullResponse;
import com.example.wsa.volunteer.exception.VolunteerControllerException;
import com.example.wsa.volunteer.exception.VolunteerException;
import com.example.wsa.volunteer.exception.VolunteerServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Volunteer Controller Class
 */
@Slf4j
@RestController("volunteerControllerV1")
@RequestMapping("api/volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    /**
     * Method to fetch upcoming approved events
     *
     * @return List<NotNullResponse < EventDTO>>
     */
    @GetMapping("/upcoming-events")
    public List<NotNullResponse<EventDTO>> upcomingEvents() {
        log.debug("VolunteerController.upcomingEvents()");
        return volunteerService.upcomingEvents().stream()
                .map(NotNullResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Method to fetch events for events calendar
     *
     * @return List<NotNullResponse < EventDTO>>
     */
    @GetMapping("/event-calendar")
    public List<NotNullResponse<EventDTO>> approvedEventsDates() {
        log.debug("VolunteerController.approvedEventsDates()");
        return volunteerService.approvedEventsDates().stream()
                .map(NotNullResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Method to fetch Volunteers details using ID
     *
     * @param id
     * @return NotNullResponse<VolunteerDTO>
     * @throws VolunteerException
     */
    @GetMapping("/{id}")
    public NotNullResponse<VolunteerDTO> getVolunteer(@PathVariable int id) throws VolunteerException {
        try {
            log.debug("VolunteerController.getVolunteer({})", id);
            return new NotNullResponse<>(volunteerService.getVolunteerDetails(id));
        } catch (VolunteerServiceException e) {
            log.error("VolunteerController.getVolunteer({})", id, e);
            throw new VolunteerControllerException("Volunteer service exception getVolunteer()", e);
        }
    }

    /**
     * Method to update Volunteer Information
     *
     * @param id
     * @param volunteer
     * @return ResponseEntity<VolunteerDTO>
     * @throws VolunteerException
     */
    @PutMapping("/{id}")
    public ResponseEntity<VolunteerDTO> updateVolunteerInfo(@PathVariable int id, @RequestBody VolunteerDTO volunteer) throws VolunteerException {
        try {
            VolunteerDTO updatedVolunteer = volunteerService.updateVolunteerInfo(id, volunteer);
            log.debug("VolunteerController.updateVolunteerInfo({}): Volunteer: {}", id, volunteer);
            return ResponseEntity.ok(updatedVolunteer);
        } catch (VolunteerServiceException e) {
            log.error("VolunteerController.updateVolunteerInfo({})", id, e);
            throw new VolunteerControllerException("Volunteer service exception updateVolunteerInfo()", e);
        }
    }

    /**
     * Method to add Volunteer Profile Picture
     *
     * @param id
     * @param payload
     * @return ResponseEntity<String>
     */
    @PostMapping("/profile-picture/{id}")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable int id, @RequestBody Map<String, String> payload) throws VolunteerException {
        try {
            String base64Image = payload.get("profileImage");
            String imageData = base64Image.split(",")[1];
            volunteerService.saveImage(imageData, id);
            log.debug("VolunteerController.uploadProfilePicture({}): Volunteer: {}", id, imageData);
            return ResponseEntity.ok("Image uploaded");
        } catch (VolunteerServiceException e) {
            log.error("VolunteerController.uploadProfilePicture({})", id, e);
            throw new VolunteerControllerException("Volunteer service exception saveImage(imageData, id)", e);
        }
    }

    @PostMapping("/saveVolunteer")
    public Integer saveVolunteer(@RequestBody Map<String, String> payload) throws VolunteerException {
        String firstName = payload.get("firstName");
        String lastName = payload.get("lastName");
        String email = payload.get("email");
        String phoneNumber = payload.get("phoneNumber");

        return volunteerService.saveVolunteer(firstName, lastName, email, phoneNumber);
    }

    /**
     * Method to fetch Volunteer Profile Picture
     *
     * @param id
     * @return ResponseEntity<byte [ ]>
     * @throws VolunteerControllerException
     */
    @GetMapping("/profile-picture/{id}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable int id) throws VolunteerException {
        log.debug("VolunteerController.getProfilePicture({})", id);
        byte[] image = volunteerService.getProfilePicture(id);
        if (image == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String contentType = null;
        try {
            contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
        } catch (IOException e) {
            log.error("VolunteerController.getProfilePicture({})", id, e);
            throw new VolunteerControllerException("Volunteer controller IO Exception : getProfilePicture(id)", e);
        }
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", contentType);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @GetMapping("/getPageVolunteer")
    public ResponseEntity<Page<VolunteerDTO>> getVolunteers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<VolunteerDTO> volunteers = volunteerService.getAllVolunteers(pageable);
        return ResponseEntity.ok(volunteers);
    }

    @PutMapping("{volunteerId}/event-accept/{eventId}")
    public ResponseEntity<String> addVolunteerToEvent(@PathVariable int volunteerId, @PathVariable int eventId) {
        volunteerService.addVolunteerToEvent(volunteerId, eventId);
        return ResponseEntity.ok("Volunteer added to the event successfully");
    }

    @GetMapping("{volunteerId}/event-accept/{eventId}")
    public ResponseEntity<Boolean> getVolunteerEvent(@PathVariable int volunteerId, @PathVariable int eventId) {
        Boolean mappingPresent = volunteerService.getVolunteerEvent(volunteerId, eventId);
        return ResponseEntity.ok(mappingPresent);
    }

    @GetMapping("volunteer-header/{id}")
    public NotNullResponse<VolunteerDTO> getVolunteerHeader(@PathVariable int id) throws VolunteerException {
        try {
            VolunteerDTO volunteerDetails = volunteerService.getVolunteerHeader(id);
            log.debug("VolunteerController.getVolunteerHeader({})", volunteerDetails);
            return new NotNullResponse<>(volunteerDetails);
        } catch (VolunteerServiceException e) {
            throw new VolunteerControllerException("Volunteer controller Exception : getVolunteerHeader(id)", e);
        }
    }
}
