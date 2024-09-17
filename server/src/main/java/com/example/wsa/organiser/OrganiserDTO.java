package com.example.wsa.organiser;

import lombok.*;

import java.time.LocalDate;

/**
 * Organiser Data Transfer Object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganiserDTO {
    private Integer id;

    private String companyName;

    private byte[] logo;

    private String postCode;

    private Boolean charity;

    private String address;

    private String telephone;

    private String email;

    private String website;

    private String mainContactName;

    private String mainContactPosition;

    private String mainContactMobileNumber;

    private LocalDate foundingDate;

    private Integer numberOfMembers;

    private String associatedClubs;

    private String turnover;

    private String membershipCategory;

    private String preferredLanguage;

    private Boolean newsletter;

    private Boolean activated;
}
