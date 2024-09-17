package com.example.wsa.organiser;

import com.example.wsa.organiser.exception.OrganiserControllerException;
import com.example.wsa.organiser.exception.OrganiserException;
import com.example.wsa.organiser.exception.OrganiserServiceException;
import com.example.wsa.utility.NotNullResponse;
import com.example.wsa.volunteer.VolunteerDTO;
import com.example.wsa.volunteer.exception.VolunteerControllerException;
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

/**
 * Organiser Controller Class
 */
@Slf4j
@RestController
@RequestMapping("/api/organiser")
public class OrganiserController {
    private final OrganiserService organiserService;

    @Autowired
    public OrganiserController(OrganiserService organiserService) {
        this.organiserService = organiserService;
    }

    /**
     * Get Organiser List
     *
     * @return List<OrganiserDTO>
     */
    @GetMapping
    public List<OrganiserDTO> getOrganiserList() {
        return organiserService.getOrganiserList();
    }

    /**
     * Get Organiser By ID
     *
     * @param id
     * @return OrganiserDTO
     * @throws OrganiserException
     */
    @GetMapping("/{id}")
    public OrganiserDTO getOrganiserById(@PathVariable int id) throws OrganiserException {
        try {
            log.debug("OrganiserController.getOrganiserById({}): ", id);
            return organiserService.getOrganiserDetails(id);
        } catch (OrganiserServiceException e) {
            throw new OrganiserControllerException("Organiser service Exception getOrganiserById()", e);
        }
    }

    /**
     * Method to add Organisation Picture/Logo
     *
     * @param id
     * @param payload
     * @return ResponseEntity<String>
     */
    @PostMapping("/profile-picture/{id}")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable int id, @RequestBody Map<String, String> payload) throws OrganiserException {
        try {
            String base64Image = payload.get("profileImage");
            String imageData = base64Image.split(",")[1];
            organiserService.saveImage(imageData, id);
            log.debug("OrganiserController.uploadProfilePicture({}): Organiser : {}", id, imageData);
            return ResponseEntity.ok("Image uploaded");
        } catch (OrganiserServiceException e) {
            log.error("OrganiserController.uploadProfilePicture({}): Exception", id, e);
            throw new OrganiserControllerException("Organiser service exception saveImage(imageData, id)", e);
        }
    }


    /**
     * Method to update Organiser Information
     *
     * @param id
     * @param organiserDTO
     * @return ResponseEntity<OrganiserDTO>
     * @throws OrganiserException
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrganiserDTO> updateOrganiserInfo(@PathVariable int id, @RequestBody OrganiserDTO organiserDTO) throws OrganiserException {
        try {
            OrganiserDTO updatedOrganiserInfo = organiserService.updateOrganiserInfo(id, organiserDTO);
            log.debug("OrganiserController.updateOrganiserInfo({}): Volunteer: {}", id, organiserDTO);
            return ResponseEntity.ok(updatedOrganiserInfo);
        } catch (OrganiserServiceException e) {
            log.error("OrganiserController.updateOrganiserInfo({})", id, e);
            throw new OrganiserControllerException("Organiser service exception updateOrganiserInfo()", e);
        }
    }

    /**
     * Method to fetch Volunteer Profile Picture
     *
     * @param id
     * @return ResponseEntity<byte [ ]>
     * @throws OrganiserControllerException
     */
    @GetMapping("/profile-picture/{id}")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable int id) throws OrganiserException {
        log.debug("OrganiserController.getProfilePicture({})", id);
        byte[] image = organiserService.getProfilePicture(id);
        if (image == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String contentType = null;
        try {
            contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(image));
        } catch (IOException e) {
            log.error("OrganiserController.getProfilePicture({})", id, e);
            throw new OrganiserControllerException("Organiser controller IO Exception : getProfilePicture(id)", e);
        }
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", contentType);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
    
    @PostMapping("/saveOrganiser")
    public Integer saveAuthOrganiser(@RequestBody Map<String, String> payload) throws OrganiserException {
        String email_id = payload.get("email_id");
        String telephone_no = payload.get("telephone_no");
        String post_code = payload.get("post_code");
        String address_link = payload.get("address_link");
        String website_link = payload.get("website_link");
        String founding_date = payload.get("founding_date");
        String associated_clubs = payload.get("associated_clubs");
        String number_of_members = payload.get("number_of_members");


        return organiserService.saveAuthOrganiser(email_id, telephone_no, post_code, address_link, website_link, founding_date, associated_clubs, number_of_members);
    }

    @GetMapping("/getPageOrganiser")
    public ResponseEntity<Page<OrganiserDTO>> getVolunteers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<OrganiserDTO> volunteers = organiserService.getPageOrganiser(pageable);
        return ResponseEntity.ok(volunteers);
    }

}
