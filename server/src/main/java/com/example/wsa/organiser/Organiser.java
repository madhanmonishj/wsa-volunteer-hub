package com.example.wsa.organiser;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/**
 * Organiser Model Class
 */
@Entity
@Table(name = "organiser")
@Data
public class Organiser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyName;
    @Lob
    @Column(name = "logo", columnDefinition = "LONGBLOB")
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

