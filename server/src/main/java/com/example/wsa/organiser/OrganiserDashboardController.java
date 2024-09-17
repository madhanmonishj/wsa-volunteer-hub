package com.example.wsa.organiser;

import com.example.wsa.event.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Controller class for handling organiser dashboard-related requests.
 * This class provides endpoints for fetching upcoming and past events for a specific organiser
 * and serving event images from the server.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/organiser-dashboard")
public class OrganiserDashboardController {

    /**
     * Repository for accessing organiser dashboard data.
     */
    private final OrganiserDashboardRepo organiserDashboardRepo;

    /**
     * Constructs an OrganiserDashboardController with the given OrganiserDashboardRepo.
     *
     * @param organiserDashboardRepo the repository for organiser dashboard operations
     */
    @Autowired
    public OrganiserDashboardController(OrganiserDashboardRepo organiserDashboardRepo) {
        this.organiserDashboardRepo = organiserDashboardRepo;
    }

    /**
     * Retrieves a list of upcoming events for the organiser.
     *
     * @return a list of EventDTO objects representing the upcoming events
     */
    @GetMapping("/events/upcoming/{id}")
    public List<EventDTO> getUpcomingEvents(@PathVariable int id) {
        return organiserDashboardRepo.getUpcomingEvents(id); // Fetches and returns upcoming events for the specific organiser.
    }

    /**
     * Retrieves a list of past events for the organiser.
     *
     * @return a list of EventDTO objects representing the past events
     */
    @GetMapping("/events/past/{id}")
    public List<EventDTO> getPastEvents(@PathVariable int id) {
        return organiserDashboardRepo.getPastEvents(id); // Fetches and returns past events for the specific organiser.
    }

    /**
     * Serves event images from the server's file system.
     *
     * @param filename the name of the image file to retrieve
     * @return a ResponseEntity containing the image resource, or a 404/500 status if not found or an error occurs
     */
    @GetMapping("/event-image/{filename:.+}")
    public ResponseEntity<Resource> getEventImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("src/main/resources/static/uploads").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
