package com.example.wsa.volunteer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * Volunteer Entity Class
 */
@Entity
@Table(name = "volunteer")
@Setter
@Getter
@ToString
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String qualifications;

    private String availability;

    private String roles;

    private String dbs;

    private String accessibilityEnhancement;

    private String about;

    private Integer rewardsEarned;

    private String emergencyContactName;

    private String emergencyPhoneNumber;

    private String emergencyRelationship;

    private String memberStatus;

    private Float rating;

    private Integer eventAttended;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    private String membershipLevel;


}
