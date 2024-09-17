//package com.example.wsa.profile_enhancement;
//
//import com.example.wsa.volunteer_signup.Volunteer;
//import com.example.wsa.volunteer_signup.VolunteerJDBCRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/profile")
//public class ProfileEnhancementController {
//
//    private final VolunteerJDBCRepo volunteerJDBCRepo;
//
//    @Autowired
//    public ProfileEnhancementController(VolunteerJDBCRepo volunteerJDBCRepo) {
//        this.volunteerJDBCRepo = volunteerJDBCRepo;
//    }
//
//    @PostMapping("/{id}")
//    public ResponseEntity<String> updateProfile(@PathVariable("id") Long id, @RequestBody Map<String, Object> payload) {
//        try {
//            Volunteer volunteer = new Volunteer();
//            volunteer.setId(id); // Set the ID to update the correct record
//
//            // Set fields with null checks
//            volunteer.setFirstName(getString(payload, "firstName"));
//            volunteer.setLastName(getString(payload, "lastName"));
//            volunteer.setEmail(getString(payload, "email"));
//            volunteer.setPhone(getString(payload, "phone"));
//
//            // Handle date parsing with null check
//            String dobString = getString(payload, "dob");
//            if (dobString != null) {
//                volunteer.setDob(LocalDate.parse(dobString));
//            }
//
//            volunteer.setAddress(getString(payload, "address"));
//            volunteer.setCity(getString(payload, "city"));
//            volunteer.setCounty(getString(payload, "county"));
//            volunteer.setPostcode(getString(payload, "postcode"));
//            volunteer.setQualifications(getString(payload, "qualifications"));
//            volunteer.setSkills(getString(payload, "skills"));
//            volunteer.setExperience(getString(payload, "experience"));
//            volunteer.setDbsCheck(getString(payload, "dbsCheck"));
//            volunteer.setConsent(getBoolean(payload, "consent"));
//            volunteer.setEmergencyContactName(getString(payload, "emergencyContactName"));
//            volunteer.setEmergencyContactPhone(getString(payload, "emergencyContactPhone"));
//            volunteer.setGoals(getString(payload, "goals"));
//            volunteer.setInterests(getString(payload, "interests"));
//            volunteer.setReferences(getString(payload, "references"));
//            volunteer.setAgreeToPolicies(getBoolean(payload, "agreeToPolicies"));
//
//            // Save or update the volunteer in the database
//            volunteerJDBCRepo.saveOrUpdate(volunteer);
//
//            return ResponseEntity.status(HttpStatus.OK).body("Profile updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile: " + e.getMessage());
//        }
//    }
//
//    private String getString(Map<String, Object> payload, String key) {
//        Object value = payload.get(key);
//        return value != null ? value.toString() : null;
//    }
//
//    private Boolean getBoolean(Map<String, Object> payload, String key) {
//        Object value = payload.get(key);
//        return value != null ? (Boolean) value : null;
//    }
//}
