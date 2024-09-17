package com.example.wsa.event;

import com.example.wsa.event.exception.EventRepositoryException;
import com.example.wsa.event.exception.EventServiceException;
import com.example.wsa.organiser.Organiser;
import com.example.wsa.organiser.OrganiserRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Event Repo Implementation Class - contains implementation logic
 */
@Slf4j
@Repository
public class EventsJDBCRepo implements EventRepo {

    private final JdbcTemplate jdbc;

    private final RowMapper<EventDTO> eventRowMapper = (rs, rowNum) -> new EventDTO(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getString("address"),
            rs.getString("landmark"),
            rs.getDate("date").toLocalDate(),
            rs.getTimestamp("start_time").toLocalDateTime(),
            rs.getTimestamp("end_time").toLocalDateTime()
    );
    private final EventRepository eventRepository;
    private final OrganiserRespository organiserRepository;

    public EventsJDBCRepo(JdbcTemplate jdbc, EventRepository eventRepository, OrganiserRespository organiserRepository) {
        this.jdbc = jdbc;
        this.eventRepository = eventRepository;
        this.organiserRepository = organiserRepository;
    }

    @Override
    public Page<EventDTO> getAllEvents(Pageable pageable) {
        String sql = "" +
                "SELECT " +
                "* " +
                "FROM event " +
                "ORDER BY date DESC " +
                "LIMIT ? " +
                "OFFSET ? ";

        String countSql = "SELECT COUNT(*) FROM event";

        // Count total records
        int total = jdbc.queryForObject(countSql, Integer.class);

        // Query paginated results
        List<EventDTO> events = jdbc.query(sql, eventRowMapper, pageable.getPageSize(), pageable.getOffset());

        return new PageImpl<>(events, pageable, total);
    }


    @Override
    @Transactional(readOnly = true)
    public EventDTO getEvent(int id) throws EventServiceException {
        List<Object[]> results = eventRepository.getEventDetailsByEventID(id);
        if (results != null && !results.isEmpty()) {
            Object[] result = results.get(0);
            return mapToEventDTO(result);
        } else {
            throw new EventRepositoryException("Error Fetching Event Details from the repository" + id);
        }
    }

    @Override
    public EventDTO updateEventInfo(int id, EventDTO event) throws EventServiceException {
        try {
            Event eventDetails = eventRepository.findById(id).orElseThrow(() -> new EventRepositoryException("Event not found with id: " + id));
            eventDetails.setTitle(event.getName());
            eventDetails.setDescription(event.getDescription());
            eventDetails.setDbsRequired(event.getDbsRequired());
            eventDetails.setAddress(event.getAddress());
            eventDetails.setCity(event.getCity());
            eventDetails.setPostalCode(event.getPostalCode());
            eventDetails.setLandmark(event.getLandmark());
            eventDetails.setRolesNeeded(String.join(",", event.getRolesNeeded()));
            eventDetails.setRewardsOffering(event.getRewardsOffering());
            eventDetails.setImage(event.getEventImage());
            eventDetails.setDate(event.getDate());
            eventDetails.setStartTime(event.getStartTime());
            eventDetails.setEndTime(event.getEndTime());
            eventDetails.setAccessibilityAssistanceProvided(String.join(",", event.getAccessibilityAssistance()));
            log.debug("EventJDBCRepo.updateEventInfo() : {}", eventDetails);
            return convertToDTO((eventRepository.save(eventDetails)), event);
        } catch (EventRepositoryException e) {
            log.error("EventJDBCRepo.updateEventInfo(): {}", e.getMessage());
            throw new EventServiceException("Event not found with id: " + id, e);
        } catch (Exception e) {
            log.error("EventJDBCRepo.updateEventInfo() Exception : {}", e.getMessage());
            throw new EventServiceException("An unexpected error occurred while fetching event details", e);
        }
    }

    @Override
    public EventDTO saveEvent(EventDTO event) throws EventServiceException {
        Event eventDetails = convertToEntity(event);
        Organiser organiser = null;
        try {
            organiser = organiserRepository.findById(event.getOrganiserID())
                    .orElseThrow(() -> new EventRepositoryException("Organiser not found"));
            eventDetails.setOrganiser(organiser);
            eventRepository.save(eventDetails);
            return convertToDTO(eventDetails, event);
        } catch (EventRepositoryException e) {
            throw new EventServiceException("Error Fetching Organiser Data", e);
        }
    }

    @Override
    public EventDTO getEventDetails(int id) throws EventServiceException {
        Event eventDetails = null;
        try {
            eventDetails = eventRepository.findById(id).orElseThrow(() -> new EventRepositoryException("Event not found with id: " + id));
        } catch (EventRepositoryException e) {
            log.error("EventJDBCRepo.getEventDetails(): {}", e.getMessage());
            throw new EventServiceException("Event not found with id: " + id, e);
        } catch (Exception e) {
            log.error("EventJDBCRepo.getEventDetails() Exception : {}", e.getMessage());
            throw new EventServiceException("An unexpected error occurred while fetching event details", e);
        }
        return eventDTOMapper(eventDetails);
    }

    private EventDTO eventDTOMapper(Event eventDetails) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventDetails.getId());
        eventDTO.setName(eventDetails.getTitle());
        eventDTO.setDescription(eventDetails.getDescription());
        eventDTO.setDbsRequired(eventDetails.getDbsRequired());
        eventDTO.setAddress(eventDetails.getAddress());
        eventDTO.setCity(eventDetails.getCity());
        eventDTO.setPostalCode(eventDetails.getPostalCode());
        eventDTO.setLandmark(eventDetails.getLandmark());
        eventDTO.setEventImage(eventDetails.getImage());
        eventDTO.setDate(eventDetails.getDate());
        eventDTO.setStartTime(eventDetails.getStartTime());
        eventDTO.setEndTime(eventDetails.getEndTime());
        eventDTO.setAccessibilityAssistance(Arrays.asList(eventDetails.getAccessibilityAssistanceProvided().split(",")));
        eventDTO.setRewardsOffering(eventDetails.getRewardsOffering());
        eventDTO.setRolesNeeded(Arrays.asList(eventDetails.getRolesNeeded().split(",")));
        return eventDTO;
    }

    private Event convertToEntity(EventDTO event) {
        log.debug("EventJDBCRepo.convertToEntity() : {}", event);
        Event eventDetails = new Event();
        eventDetails.setTitle(event.getName());
        eventDetails.setDescription(event.getDescription());
        eventDetails.setDbsRequired(event.getDbsRequired());
        eventDetails.setAddress(event.getAddress());
        eventDetails.setCity(event.getCity());
        eventDetails.setPostalCode(event.getPostalCode());
        eventDetails.setLandmark(event.getLandmark());
        eventDetails.setRolesNeeded(String.join(",", event.getRolesNeeded()));
        eventDetails.setRewardsOffering(event.getRewardsOffering());
        String base64Image = event.getBase64Image();
        if (base64Image.contains(",")) {
            base64Image = base64Image.split(",")[1];
        }
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        eventDetails.setImage(imageBytes);
        eventDetails.setDate(event.getDate());
        eventDetails.setStartTime(event.getStartTime());
        eventDetails.setEndTime(event.getEndTime());
        eventDetails.setAccessibilityAssistanceProvided(String.join(",", event.getAccessibilityAssistance()));
        eventDetails.setApproved(false);
        eventDetails.setRewardsOffering(event.getRewardsOffering());
        return eventDetails;
    }

    private EventDTO convertToDTO(Event event, EventDTO eventOrg) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getTitle());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setDbsRequired(event.getDbsRequired());
        eventDTO.setAddress(event.getAddress());
        eventDTO.setCity(event.getCity());
        eventDTO.setPostalCode(event.getPostalCode());
        eventDTO.setLandmark(event.getLandmark());
        eventDTO.setRolesNeeded(Arrays.asList(event.getRolesNeeded().split(",")));
        eventDTO.setRewardsOffering(event.getRewardsOffering());
        eventDTO.setEventImage(event.getImage());
        eventDTO.setDate(event.getDate());
        eventDTO.setStartTime(event.getStartTime());
        eventDTO.setEndTime(event.getEndTime());
        eventDTO.setOrganiserID(eventOrg.getOrganiserID());
        eventDTO.setOrganiserName(eventOrg.getOrganiserName());
        return eventDTO;

    }

    private EventDTO mapToEventDTO(Object[] result) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setOrganiserID((Integer) result[0]);
        eventDTO.setOrganiserName((String) result[1]);
        eventDTO.setId((Integer) result[2]);
        eventDTO.setName((String) result[3]);
        eventDTO.setDescription((String) result[4]);
        eventDTO.setDbsRequired((Boolean) result[5]);
        eventDTO.setAddress((String) result[6]);
        eventDTO.setCity((String) result[7]);
        eventDTO.setPostalCode((String) result[8]);
        eventDTO.setLandmark((String) result[9]);
        eventDTO.setRolesNeeded(Arrays.asList(((String) result[10]).split(",")));
        eventDTO.setRewardsOffering((String) result[11]);
        eventDTO.setEventImage((byte[]) result[12]);
        eventDTO.setDate(((Date) result[13]).toLocalDate());
        eventDTO.setStartTime(((Timestamp) result[14]).toLocalDateTime());
        eventDTO.setEndTime(((Timestamp) result[15]).toLocalDateTime());
        eventDTO.setAccessibilityAssistance(Arrays.asList(((String) result[16]).split(",")));
        return eventDTO;
    }
}
