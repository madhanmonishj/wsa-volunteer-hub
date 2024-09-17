package com.example.wsa.volunteer;

import com.example.wsa.event.EventDTO;
import com.example.wsa.volunteer.exception.VolunteerServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Volunteer Service Interface
 */
public interface VolunteerService {

    List<EventDTO> upcomingEvents();

    List<EventDTO> approvedEventsDates();

    VolunteerDTO getVolunteerDetails(Integer id) throws VolunteerServiceException;

    VolunteerDTO updateVolunteerInfo(int id, VolunteerDTO volunteer) throws VolunteerServiceException;

    void saveImage(String image, int id) throws VolunteerServiceException;

    byte[] getProfilePicture(int id);

    Page<VolunteerDTO> getAllVolunteers(Pageable pageable);

    void addVolunteerToEvent(int volunteerId, int eventId);

    Boolean getVolunteerEvent(int volunteerId, int eventId);

    Integer saveVolunteer (String firstName, String lastName, String email, String phoneNumber);

    VolunteerDTO getVolunteerHeader(int id) throws VolunteerServiceException;
}
