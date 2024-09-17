package com.example.wsa.volunteer;

import com.example.wsa.event.EventDTO;
import com.example.wsa.event.EventRepository;
import com.example.wsa.volunteer.exception.VolunteerRepositoryException;
import com.example.wsa.volunteer.exception.VolunteerServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Volunteer Service Implementation Class - implementation logic
 */
@Slf4j
@Service
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final EventRepository eventRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VolunteerServiceImpl(VolunteerRepository volunteerRepository, EventRepository eventRepository, JdbcTemplate jdbcTemplate) {
        this.volunteerRepository = volunteerRepository;
        this.eventRepository = eventRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Method to fetch Upcoming Events using EventRepository findUpcomingEvents() method
     *
     * @return List<EventDTO>
     */
    @Override
    public List<EventDTO> upcomingEvents() {
        List<Object[]> results = eventRepository.findUpcomingEvents();
        log.debug("VolunteerServiceImpl.upcomingEvents()");
        return results.stream().map(this::upcomingEventsMapper).collect((Collectors.toList()));
    }


    /**
     * Method to fetch Upcoming Events using EventRepository findApprovedEventsDates() method
     *
     * @return List<EventDTO> approvedEventsDates()
     */
    @Override
    public List<EventDTO> approvedEventsDates() {
        List<Object[]> results = eventRepository.findApprovedEventsDates();
        log.debug("VolunteerServiceImpl.approvedEventsDates()");
        return results.stream().map(this::approvedEventsDatesMapper).collect((Collectors.toList()));
    }

    /**
     * Method to fetch Volunteers Details using volunteerRepository findById(id)
     *
     * @param id
     * @return VolunteerDTO
     * @throws VolunteerServiceException
     */
    @Override
    public VolunteerDTO getVolunteerDetails(Integer id) throws VolunteerServiceException {
        try {
            Volunteer volunteerDetails = volunteerRepository.findById(id)
                    .orElseThrow(() -> new VolunteerRepositoryException("Volunteer not found with id: " + id));
            log.debug("VolunteerServiceImpl.getVolunteerDetails() : {}", volunteerDetails);
            return convertToDto(volunteerDetails);
        } catch (VolunteerRepositoryException e) {
            log.error("VolunteerServiceImpl.getVolunteerDetails() : {}", e.getMessage());
            throw new VolunteerServiceException("Volunteer not found with id: " + id, e);
        } catch (Exception e) {
            log.error("VolunteerServiceImpl.getVolunteerDetails() Exception : {}", e.getMessage());
            throw new VolunteerServiceException("An unexpected error occurred while fetching volunteer details", e);
        }
    }

    /**
     * Method to fetch Volunteers Details using volunteerRepository findById(id)
     * and update the Volunteer details
     *
     * @param id
     * @param volunteerDTO
     * @return volunteerDTO
     * @throws VolunteerServiceException
     */
    @Override
    public VolunteerDTO updateVolunteerInfo(int id, VolunteerDTO volunteerDTO) throws VolunteerServiceException {
        try {
            Volunteer volunteerDetails = volunteerRepository.findById(id)
                    .orElseThrow(() -> new VolunteerRepositoryException("Volunteer not found with id: " + id));
            volunteerDetails.setFirstName(volunteerDTO.getFirstName());
            volunteerDetails.setLastName(volunteerDTO.getLastName());
            volunteerDetails.setGender(volunteerDTO.getGender());
            volunteerDetails.setDob(volunteerDTO.getDob());
            volunteerDetails.setEmail(volunteerDTO.getEmail());
            volunteerDetails.setPhoneNumber(volunteerDTO.getPhoneNumber());
            volunteerDetails.setAddress(volunteerDTO.getAddress());
            volunteerDetails.setPostalCode(volunteerDTO.getPostalCode());
            volunteerDetails.setOccupation(String.join(",", volunteerDTO.getOccupation()));
            volunteerDetails.setQualifications(String.join(",", volunteerDTO.getQualifications()));
            volunteerDetails.setAvailability(String.join(",", volunteerDTO.getAvailability()));
            volunteerDetails.setRoles(String.join(",", volunteerDTO.getRoles()));
            volunteerDetails.setDbs(volunteerDTO.getDbs());
            volunteerDetails.setAccessibilityEnhancement(String.join(",", volunteerDTO.getAccessibilityEnhancement()));
            volunteerDetails.setAbout(volunteerDTO.getAbout());
            volunteerDetails.setEmergencyContactName(volunteerDTO.getEmergencyContactName());
            volunteerDetails.setEmergencyPhoneNumber(volunteerDTO.getEmergencyPhoneNumber());
            volunteerDetails.setEmergencyRelationship(volunteerDTO.getEmergencyRelationship());
            log.debug("VolunteerServiceImpl.updateVolunteerInfo() : {}", volunteerDetails);
            return convertToDto(volunteerRepository.save(volunteerDetails));
        } catch (VolunteerRepositoryException e) {
            log.error("VolunteerServiceImpl.updateVolunteerInfo(): {}", e.getMessage());
            throw new VolunteerServiceException("Volunteer not found with id: " + id, e);
        } catch (Exception e) {
            log.error("VolunteerServiceImpl.updateVolunteerInfo() Exception : {}", e.getMessage());
            throw new VolunteerServiceException("An unexpected error occurred while fetching volunteer details", e);
        }

    }

    /**
     * Method to save Volunteers Profile Picture using volunteerRepository findById(id) and save(volunteer)
     *
     * @param image
     * @param id
     * @throws VolunteerServiceException
     */
    @Override
    public void saveImage(String image, int id) throws VolunteerServiceException {
        try {
            Volunteer volunteerDetails = volunteerRepository.findById(id)
                    .orElseThrow(() -> new VolunteerRepositoryException("Volunteer not found with id: " + id));
            byte[] imageBytes = Base64.getDecoder().decode(image);
            volunteerDetails.setImage(imageBytes);
            volunteerRepository.save(volunteerDetails);
            log.debug("VolunteerServiceImpl.saveImage() : {}", id);
        } catch (VolunteerRepositoryException e) {
            log.error("VolunteerServiceImpl.saveImage(): {}", e.getMessage());
            throw new VolunteerServiceException("Volunteer not found with id: " + id, e);
        } catch (Exception e) {
            log.error("VolunteerServiceImpl.saveImage() Exception:  {}", e.getMessage());
            throw new VolunteerServiceException("An unexpected error occurred while fetching volunteer details", e);
        }


    }

    /**
     * Method to get Volunteers Profile Picture using volunteerRepository stored procedure GetVolunteerProfilePicture
     *
     * @param id
     * @return byte[]
     */
    @Override
    public byte[] getProfilePicture(int id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetVolunteerProfilePicture");
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.setParameter(1, id);
        return (byte[]) query.getSingleResult();
    }

    @Override
    @Transactional
    public void addVolunteerToEvent(int volunteerId, int eventId) {
        String sql = "INSERT INTO volunteer_event (volunteer_id, event_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, volunteerId, eventId);
    }

    @Override
    public Boolean getVolunteerEvent(int volunteerId, int eventId) {
        String sql = "SELECT EXISTS (SELECT 1 FROM volunteer_event WHERE volunteer_id = ? AND event_id = ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{volunteerId, eventId}, Boolean.class);
    }

    /**
     * Method to convert Volunteer -> VolunteerDTO object
     *
     * @param volunteer
     * @return VolunteerDTO
     */
    private VolunteerDTO convertToDto(Volunteer volunteer) {
        return new VolunteerDTO(
                volunteer.getId(),
                volunteer.getFirstName(),
                volunteer.getLastName(),
                volunteer.getGender(),
                volunteer.getDob(),
                volunteer.getEmail(),
                volunteer.getPhoneNumber(),
                volunteer.getAddress(),
                volunteer.getPostalCode(),
                volunteer.getOccupation(),
                Arrays.asList(volunteer.getAvailability().split(",")),
                Arrays.asList(volunteer.getQualifications().split(",")),
                Arrays.asList(volunteer.getRoles().split(",")),
                volunteer.getDbs(),
                Arrays.asList(volunteer.getAccessibilityEnhancement().split(",")),
                volunteer.getAbout(),
                volunteer.getRewardsEarned(),
                volunteer.getEmergencyContactName(),
                volunteer.getEmergencyPhoneNumber(),
                volunteer.getEmergencyRelationship(),
                volunteer.getMemberStatus(),
                volunteer.getRating(),
                volunteer.getEventAttended(),
                volunteer.getMembershipLevel(),
                volunteer.getImage()
        );
    }

    /**
     * Method to map  id, title, date to EventDTO
     *
     * @param result
     * @return EventDTO
     */
    private EventDTO approvedEventsDatesMapper(Object[] result) {
        Integer id = (Integer) result[0];
        String name = (String) result[1];
        LocalDate date = ((Date) result[2]).toLocalDate();
        return new EventDTO(id, name, date);
    }

    /**
     * Method to map id,title,image,postal_code,city,date,day_of_week to EventDTO
     *
     * @param result
     * @return EventDTO
     */
    private EventDTO upcomingEventsMapper(Object[] result) {
        Integer id = (Integer) result[0];
        String name = (String) result[1];
        byte[] image = (byte[]) result[2];
        String postalCode = (String) result[3];
        String city = (String) result[4];
        LocalDate date = ((Date) result[5]).toLocalDate();
        String dayOfWeek = (String) result[6];
        return new EventDTO(id, name, image, postalCode, city, date, dayOfWeek);
    }

    /**
     * Method to get Volunteers Profile Picture using volunteerRepository stored
     * procedure GetVolunteerProfilePicture
     *
     * @param id
     * @return byte[]
     */
    @Override
    public Page<VolunteerDTO> getAllVolunteers(Pageable pageable) {
        // Create a stored procedure query
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("getPageVolunteer", Volunteer.class);

        // Register parameters (if your stored procedure requires them)
        query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);

        // Set the parameters for pagination
        query.setParameter(1, pageable.getPageNumber());
        query.setParameter(2, pageable.getPageSize());

        // Get the list of Volunteer entities directly
        @SuppressWarnings("unchecked")
        List<Volunteer> volunteers = query.getResultList();

        // Convert the Volunteer entities to VolunteerDTOs using the existing
        // convertToDto method
        List<VolunteerDTO> volunteerDTOs = volunteers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // Return the paginated list of VolunteerDTOs
        return new PageImpl<>(volunteerDTOs, pageable, volunteers.size());
    }

    @Override
    public Integer saveVolunteer(String firstName, String lastName, String email, String phoneNumber) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("SaveVolunteerAuth");

        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // first name
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); // last name
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN); // email
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN); // phone number
        query.registerStoredProcedureParameter(5, Integer.class, ParameterMode.OUT); // returning volunteer id

        query.setParameter(1, firstName);
        query.setParameter(2, lastName);
        query.setParameter(3, email);
        query.setParameter(4, phoneNumber);

        // Execute the stored procedure
        query.execute();

        return (Integer) query.getOutputParameterValue(5);
    }

    @Override
    public VolunteerDTO getVolunteerHeader(int id) throws VolunteerServiceException {
        try {
            Volunteer volunteerDetails = volunteerRepository.findById(id)
                    .orElseThrow(() -> new VolunteerRepositoryException("Volunteer not found with id: " + id));
            log.debug("VolunteerServiceImpl.getVolunteerHeader() : {}", id);
            return convertToDTOHeader(volunteerDetails);
        } catch (VolunteerRepositoryException e) {
            log.error("VolunteerServiceImpl.getVolunteerHeader(): {}", e.getMessage());
            throw new VolunteerServiceException("Volunteer not found with id: " + id, e);
        } catch (Exception e) {
            log.error("VolunteerServiceImpl.getVolunteerHeader() Exception:  {}", e.getMessage());
            throw new VolunteerServiceException("An unexpected error occurred while fetching volunteer details", e);
        }
    }

    private VolunteerDTO convertToDTOHeader(Volunteer volunteerDetails) {
        VolunteerDTO volunteerDTO = new VolunteerDTO();
        volunteerDTO.setImage(volunteerDetails.getImage());
        volunteerDTO.setFirstName(volunteerDetails.getFirstName());
        return volunteerDTO;
    }


}
