package com.example.wsa.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

/**
 * Repository class for handling event form database operations.
 * This class uses JdbcTemplate to interact with the database for CRUD operations related to events.
 */
@Repository
public class EventFormJDBC {

    /**
     * The JdbcTemplate instance used for database operations.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Saves event data into the database.
     *
     * @param eventDTO the EventDTO object containing event data to be saved
     */
    public void saveEvent(EventDTO eventDTO) {
        // SQL query to insert event data into the event table
        String sql = "INSERT INTO event (title, description, address, city, postal_code, landmark, date, start_time, end_time, dbs_required, accessibility_assistance_provided, roles_needed, image, rewards_offering) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Executing the SQL query with event data
        jdbcTemplate.update(sql,
                eventDTO.getName(),                             // Event title
                eventDTO.getDescription(),                       // Event description
                eventDTO.getAddress(),                           // Event address
                eventDTO.getCity(),                              // Event city
                eventDTO.getPostalCode(),                        // Event postal code
                eventDTO.getLandmark(),                          // Event landmark
                eventDTO.getDate(),                              // Event date
                eventDTO.getStartTime(),                         // Event start time
                eventDTO.getEndTime(),                           // Event end time
                eventDTO.getDbsRequired(),                       // DBS required
                String.join(",", eventDTO.getAccessibilityAssistance()),   // Accessibility assistance provided
                String.join(",", eventDTO.getRolesNeeded()),     // Roles needed
                eventDTO.getEventImage(),                        // Main image bytes
                eventDTO.getRewardsOffering()                    // Rewards offering
        );

        // Log data to console to confirm insertion
        System.out.println("Event saved: " + eventDTO.getName());
    }

    /**
     * Updates existing event data in the database.
     *
     * @param id the ID of the event to be updated
     * @param eventDTO the EventDTO object containing the updated event data
     */
    public void updateEvent(int id, EventDTO eventDTO) {
        // SQL query to update event data in the event table
        String sql = "UPDATE event SET title = ?, description = ?, address = ?, city = ?, postal_code = ?, landmark = ?, date = ?, start_time = ?, end_time = ?, dbs_required = ?, accessibility_assistance_provided = ?, roles_needed = ?, rewards_offering = ?, approved = ?";
        if (eventDTO.getEventImage() != null) {
            sql += ", image = ?";
        }
        sql += " WHERE id = ?";

        // Executing the SQL query with updated event data
        if (eventDTO.getEventImage() != null) {
            jdbcTemplate.update(sql,
                    eventDTO.getName(),
                    eventDTO.getDescription(),
                    eventDTO.getAddress(),
                    eventDTO.getCity(),
                    eventDTO.getPostalCode(),
                    eventDTO.getLandmark(),
                    eventDTO.getDate(),
                    eventDTO.getStartTime(),
                    eventDTO.getEndTime(),
                    eventDTO.getDbsRequired(),
                    String.join(",", eventDTO.getAccessibilityAssistance()),
                    String.join(",", eventDTO.getRolesNeeded()),
                    eventDTO.getRewardsOffering(),
                    eventDTO.getApproved(),
                    eventDTO.getEventImage(),
                    id

            );
        } else {
            jdbcTemplate.update(sql,
                    eventDTO.getName(),
                    eventDTO.getDescription(),
                    eventDTO.getAddress(),
                    eventDTO.getCity(),
                    eventDTO.getPostalCode(),
                    eventDTO.getLandmark(),
                    eventDTO.getDate(),
                    eventDTO.getStartTime(),
                    eventDTO.getEndTime(),
                    eventDTO.getDbsRequired(),
                    String.join(",", eventDTO.getAccessibilityAssistance()),
                    String.join(",", eventDTO.getRolesNeeded()),
                    eventDTO.getRewardsOffering(),
                    eventDTO.getApproved(),
                    id
            );
        }

        // Log data to console to confirm update
        System.out.println("Event updated: " + eventDTO.getName());
    }

    /**
     * Fetches event data by ID from the database.
     *
     * @param id the ID of the event to retrieve
     * @return an Optional containing the EventDTO if found, or an empty Optional if not found
     */
    public Optional<EventDTO> getEventById(int id) {
        // SQL query to select event data by ID
        String sql = "SELECT * FROM event WHERE id = ?";
        try {
            EventDTO event = jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<EventDTO>() {
                @Override
                public EventDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                    EventDTO eventDTO = new EventDTO();
                    eventDTO.setId(rs.getInt("id"));
                    eventDTO.setName(rs.getString("title"));
                    eventDTO.setDescription(rs.getString("description"));
                    eventDTO.setAddress(rs.getString("address"));
                    eventDTO.setCity(rs.getString("city"));
                    eventDTO.setPostalCode(rs.getString("postal_code"));
                    eventDTO.setLandmark(rs.getString("landmark"));
                    eventDTO.setDate(rs.getDate("date").toLocalDate());
                    eventDTO.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                    eventDTO.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
                    eventDTO.setDbsRequired(rs.getBoolean("dbs_required"));
                    eventDTO.setAccessibilityAssistance(Arrays.asList(rs.getString("accessibility_assistance_provided").split(",")));
                    eventDTO.setRolesNeeded(Arrays.asList(rs.getString("roles_needed").split(",")));
                    eventDTO.setRewardsOffering(rs.getString("rewards_offering"));

                    // Get image bytes directly from ResultSet
                    byte[] imageBytes = rs.getBytes("image");
                    eventDTO.setEventImage(imageBytes); // Set the image bytes directly to eventDTO

                    return eventDTO;
                }
            });
            return Optional.of(event);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
