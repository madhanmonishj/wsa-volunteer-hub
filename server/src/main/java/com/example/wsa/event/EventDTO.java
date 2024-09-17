package com.example.wsa.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Event Data Transfer Object Class
 */
@Setter
@Getter
@ToString
public class EventDTO {
    private Integer id;

    private  String name;

    private String description;

    private Boolean dbsRequired;

    private String address;

    private String city;

    private String postalCode;

    private String landmark;

    private List<String> rolesNeeded;

    private String rewardsOffering;

    private byte[] eventImage;

    private LocalDate date;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private List<String> accessibilityAssistance;

    private Boolean approved;

    private String dayOfWeek;

    private String volunteer;

    private int organiserID;

    private String organiserName;

    private String base64Image;

    public EventDTO (){};

    public EventDTO( Integer id,String name, byte[] eventImage, String postalCode, String city, LocalDate date, String dayOfWeek) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.postalCode = postalCode;
        this.eventImage = eventImage;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    public EventDTO( Integer id, String name, String description, String address, String landmark, LocalDate date, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.landmark = landmark;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public EventDTO(Integer id, String name, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }


}
