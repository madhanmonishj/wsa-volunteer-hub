package com.example.wsa.volunteer_signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {
    private Long id; // Unique identifier for each volunteer
    private String username; // Username for login
    private String password; // Password for login
    private String firstName; // First name of the volunteer
    private String lastName; // Last name of the volunteer
    private String email; // Email address

    // Optional fields
    private String phoneNumber = null; // Phone number
    private LocalDate dob = null; // Date of birth
    private String address = null; // Street address
    private String postalCode = null; // Postal code
    private String occupation = null; // Occupation
    private String qualifications = null; // List of qualifications
    private String availability = null; // Availability
    private String roles = null; // Roles
    private String dbs = null; // DBS check status
    private String accessibilityEnhancement = null; // Accessibility enhancements
    private String about = null; // About the volunteer
    private Integer rewardsEarned = null; // Rewards earned
    private String emergencyContactName = null; // Emergency contact's name
    private String emergencyPhoneNumber = null; // Emergency contact's phone number
    private String emergencyRelationship = null; // Emergency contact's relationship
    private String goals = null; // Volunteer goals
    private String interests = null; // Volunteer interests
    private String references = null; // References
    private Boolean agreeToPolicies = null; // Agreement to terms and policies
    private String memberStatus = null; // Member status
    private Float rating = null; // Volunteer rating
    private String membershipLevel = null; // Membership level
    private Integer eventAttended = null; // Events attended
    private String gender = null; // Gender

    // Assuming image is stored as a byte array
    private byte[] image = null; // Volunteer image
}
