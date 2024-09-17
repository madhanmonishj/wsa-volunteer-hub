package com.example.wsa.volunteer_signup;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class SignUpRepo_imp implements SignUpRepo {

    private final JdbcTemplate jdbc;
    private RowMapper<Volunteer> volunteerItemMapper;

    public SignUpRepo_imp(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
        setVolunteerItemMapper();
    }

    private void setVolunteerItemMapper() {
        volunteerItemMapper = (rs, i) -> new Volunteer(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getObject("dob", LocalDate.class),  // Use getObject for nullable date
                rs.getString("address"),
                rs.getString("postal_code"),
                rs.getString("occupation"),
                rs.getString("qualifications"),
                rs.getString("availability"),
                rs.getString("roles"),
                rs.getString("dbs"),
                rs.getString("accessibility_enhancement"),
                rs.getString("about"),
                rs.getInt("rewards_earned"),
                rs.getString("emergency_contact_name"),
                rs.getString("emergency_phone_number"),
                rs.getString("emergency_relationship"),
                rs.getString("goals"),
                rs.getString("interests"),
                rs.getString("references"),
                rs.getBoolean("agree_to_policies"),
                rs.getString("member_status"),
                rs.getFloat("rating"),
                rs.getString("membership_level"),
                rs.getInt("event_attended"),
                rs.getString("gender"),
                rs.getBytes("image")  // Assuming the image is stored as a byte array
        );
    }
    public void saveVolunteer(Volunteer volunteer) {
        String sql = "INSERT INTO volunteer (username, password, first_name, last_name, email, phone_number, dob, address, postal_code, occupation, " +
                "qualifications, availability, roles, dbs, accessibility_enhancement, about, rewards_earned, emergency_contact_name, emergency_phone_number, " +
                "emergency_relationship, goals, interests, `references`, agree_to_policies, member_status, rating, membership_level, event_attended, gender, image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbc.update(sql,
                volunteer.getUsername(),
                volunteer.getPassword(),
                volunteer.getFirstName(),
                volunteer.getLastName(),
                volunteer.getEmail(),
                volunteer.getPhoneNumber(),
                volunteer.getDob(),
                volunteer.getAddress(),
                volunteer.getPostalCode(),
                volunteer.getOccupation(),
                volunteer.getQualifications(),
                volunteer.getAvailability(),
                volunteer.getRoles(),
                volunteer.getDbs(),
                volunteer.getAccessibilityEnhancement(),
                volunteer.getAbout(),
                volunteer.getRewardsEarned(),
                volunteer.getEmergencyContactName(),
                volunteer.getEmergencyPhoneNumber(),
                volunteer.getEmergencyRelationship(),
                volunteer.getGoals(),
                volunteer.getInterests(),
                volunteer.getReferences(),
                volunteer.getAgreeToPolicies(),
                volunteer.getMemberStatus(),
                volunteer.getRating(),
                volunteer.getMembershipLevel(),
                volunteer.getEventAttended(),
                volunteer.getGender(),
                volunteer.getImage()
        );
    }

    @Override
    public void addUserEntry(Volunteer volunteer) {
        String username = volunteer.getUsername();
        String password = volunteer.getPassword();
        String email = volunteer.getEmail();

        String sql = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
        jdbc.update(sql, username, password, email);
    }

    public Volunteer getVolunteerById(Long id) {
        String sql = "SELECT * FROM volunteer WHERE id = ?";
        return jdbc.queryForObject(sql, volunteerItemMapper, id);
    }
}
