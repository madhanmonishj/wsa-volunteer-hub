package com.example.wsa.volunteer;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Volunteer Data Transfer Object
 */
@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class VolunteerDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private String gender;

    private LocalDate dob;

    private String email;

    private String phoneNumber;

    private String address;

    private String postalCode;

    private String occupation;

    private List<String> qualifications;

    private List<String> availability;

    private List<String> roles;

    private String dbs;

    private List<String> accessibilityEnhancement;

    private String about;

    private Integer rewardsEarned;

    private String emergencyContactName;

    private String emergencyPhoneNumber;

    private String emergencyRelationship;

    private String memberStatus;

    private Float rating;

    private Integer eventAttended;

    private String membership;

    private byte[] image;


    public VolunteerDTO(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
