package com.example.wsa.event;

import com.example.wsa.organiser.Organiser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Event Model Class
 */
@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private Boolean dbsRequired;

    private String address;

    private String city;

    private String postalCode;

    private String landmark;

    private String rolesNeeded;

    private String rewardsOffering;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    private LocalDate date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String accessibilityAssistanceProvided;

    @ManyToOne
    @JoinColumn(name = "organiser_id", referencedColumnName = "id")
    private Organiser organiser;

    private Boolean approved;

}
