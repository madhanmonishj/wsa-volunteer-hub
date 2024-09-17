package com.example.wsa.volunteer_signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping
    public ResponseEntity<String> registerVolunteer(@RequestBody Map<String, Object> payload) {
        try {
            Volunteer volunteer = new Volunteer();

            // Essential fields
            volunteer.setFirstName((String) payload.get("firstName"));
            volunteer.setLastName((String) payload.get("lastName"));
            volunteer.setUsername((String) payload.get("username"));
            volunteer.setPassword((String) payload.get("password"));
            volunteer.setEmail((String) payload.get("email"));

            // Optional fields
            volunteer.setPhoneNumber((String) payload.get("phoneNumber"));
            volunteer.setDob(payload.get("dob") != null ? LocalDate.parse((String) payload.get("dob")) : null);
            volunteer.setAddress((String) payload.get("address"));
            volunteer.setPostalCode((String) payload.get("postalCode"));
            volunteer.setOccupation((String) payload.get("occupation"));
            volunteer.setQualifications((String) payload.get("qualifications"));
            volunteer.setAvailability((String) payload.get("availability"));
            volunteer.setRoles((String) payload.get("roles"));
            volunteer.setDbs((String) payload.get("dbs"));
            volunteer.setAccessibilityEnhancement((String) payload.get("accessibilityEnhancement"));
            volunteer.setAbout((String) payload.get("about"));
            volunteer.setRewardsEarned(payload.get("rewardsEarned") != null ? (Integer) payload.get("rewardsEarned") : 0);
            volunteer.setEmergencyContactName((String) payload.get("emergencyContactName"));
            volunteer.setEmergencyPhoneNumber((String) payload.get("emergencyPhoneNumber"));
            volunteer.setEmergencyRelationship((String) payload.get("emergencyRelationship"));
            volunteer.setGoals((String) payload.get("goals"));
            volunteer.setInterests((String) payload.get("interests"));
            volunteer.setReferences((String) payload.get("references"));
            volunteer.setAgreeToPolicies(payload.get("agreeToPolicies") != null && (Boolean) payload.get("agreeToPolicies"));
            volunteer.setMemberStatus((String) payload.get("memberStatus"));
            volunteer.setRating(payload.get("rating") != null ? (Float) payload.get("rating") : null);
            volunteer.setMembershipLevel((String) payload.get("membershipLevel"));
            volunteer.setEventAttended(payload.get("eventAttended") != null ? (Integer) payload.get("eventAttended") : null);
            volunteer.setGender((String) payload.get("gender"));
            // Image field handling depends on how you plan to process and store the image data
            // Assuming it's a base64 encoded string in the payload
            if (payload.get("image") != null) {
                byte[] imageBytes = java.util.Base64.getDecoder().decode((String) payload.get("image"));
                volunteer.setImage(imageBytes);
            }

            // Save the volunteer to the database. Add corresponding user entry for Ozair's feature
            signUpService.saveVolunteer(volunteer);
            signUpService.addUserEntry(volunteer);

            return ResponseEntity.status(HttpStatus.CREATED).body("Volunteer registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering volunteer: " + e.getMessage());
        }
    }
}
