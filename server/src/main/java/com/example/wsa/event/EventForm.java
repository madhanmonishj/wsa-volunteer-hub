package com.example.wsa.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * EventForm class represents the data structure for an event form, including
 * fields for event details such as title, date, time, and location, as well as
 * validation annotations to ensure required fields are provided.
 */
public class EventForm {

    /**
     * The title of the event.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Event title is required")
    private String title;

    /**
     * The date of the event.
     * This field is required and cannot be null.
     */
    @NotNull(message = "Event date is required")
    private Date date;

    /**
     * The start time of the event.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Start time is required")
    private String startTime;

    /**
     * The end time of the event.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "End time is required")
    private String endTime;

    /**
     * A description of the event.
     */
    private String description = "";

    /**
     * The address where the event will take place.
     */
    private String address = "";

    /**
     * The city where the event will take place.
     */
    private String city = "";

    /**
     * The postal code for the event location.
     */
    private String postalCode = "";

    /**
     * A landmark near the event location.
     */
    private String landmark = "";

    /**
     * Indicates whether a DBS (Disclosure and Barring Service) check is required
     * for the event.
     */
    private Boolean dbsRequired = false;

    /**
     * Describes any accessibility assistance provided at the event.
     */
    private String accessibilityAssistanceProvided = "";

    /**
     * The main image for the event.
     * This field is required and cannot be null.
     */
    @NotNull(message = "Main image is required")
    private MultipartFile mainImage;

    /**
     * The main image as a byte array after conversion.
     */
    private byte[] image;

    /**
     * A comma-separated string listing the roles needed for the event.
     */
    private String rolesNeeded = "";

    /**
     * A list of volunteers assigned to the event.
     */
    private List<String> volunteers;

    /**
     * A comma-separated string listing the rewards offered for participating in the
     * event.
     */
    private String rewardsOffering = "";

    // Getters and setters for each field

    /**
     * Gets the title of the event.
     *
     * @return the title of the event
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the event.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the date of the event.
     *
     * @return the date of the event
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the event.
     *
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the start time of the event.
     *
     * @return the start time of the event
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the event.
     *
     * @param startTime the start time to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time of the event.
     *
     * @return the end time of the event
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the event.
     *
     * @param endTime the end time to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the description of the event.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the event.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the address of the event.
     *
     * @return the address of the event
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the event.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the city where the event will take place.
     *
     * @return the city of the event
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where the event will take place.
     *
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the postal code for the event location.
     *
     * @return the postal code of the event
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code for the event location.
     *
     * @param postalCode the postal code to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the landmark near the event location.
     *
     * @return the landmark near the event
     */
    public String getLandmark() {
        return landmark;
    }

    /**
     * Sets the landmark near the event location.
     *
     * @param landmark the landmark to set
     */
    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    /**
     * Gets whether a DBS check is required for the event.
     *
     * @return true if a DBS check is required, false otherwise
     */
    public Boolean getDbsRequired() {
        return dbsRequired;
    }

    /**
     * Sets whether a DBS check is required for the event.
     *
     * @param dbsRequired the dbsRequired to set
     */
    public void setDbsRequired(Boolean dbsRequired) {
        this.dbsRequired = dbsRequired;
    }

    /**
     * Gets the accessibility assistance provided at the event.
     *
     * @return the accessibility assistance provided
     */
    public String getAccessibilityAssistanceProvided() {
        return accessibilityAssistanceProvided;
    }

    /**
     * Sets the accessibility assistance provided at the event.
     *
     * @param accessibilityAssistanceProvided the accessibility assistance to set
     */
    public void setAccessibilityAssistanceProvided(String accessibilityAssistanceProvided) {
        this.accessibilityAssistanceProvided = accessibilityAssistanceProvided;
    }

    /**
     * Gets the main image for the event.
     *
     * @return the main image
     */
    public MultipartFile getMainImage() {
        return mainImage;
    }

    /**
     * Sets the main image for the event.
     *
     * @param mainImage the main image to set
     */
    public void setMainImage(MultipartFile mainImage) {
        this.mainImage = mainImage;
    }

    /**
     * Gets the image as a byte array.
     *
     * @return the image as bytes
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the image as a byte array.
     *
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Gets the roles needed for the event as a comma-separated string.
     *
     * @return the roles needed for the event
     */
    public String getRolesNeeded() {
        return rolesNeeded;
    }

    /**
     * Sets the roles needed for the event as a comma-separated string.
     *
     * @param rolesNeeded the roles needed to set
     */
    public void setRolesNeeded(String rolesNeeded) {
        this.rolesNeeded = rolesNeeded;
    }

    /**
     * Gets the list of volunteers for the event.
     *
     * @return the list of volunteers
     */
    public List<String> getVolunteers() {
        return volunteers;
    }

    /**
     * Sets the list of volunteers for the event.
     *
     * @param volunteers the volunteers to set
     */
    public void setVolunteers(List<String> volunteers) {
        this.volunteers = volunteers;
    }

    /**
     * Gets the rewards offering for the event as a comma-separated string.
     *
     * @return the rewards offering for the event
     */
    public String getRewardsOffering() {
        return rewardsOffering;
    }

    /**
     * Sets the rewards offering for the event as a comma-separated string.
     *
     * @param rewardsOffering the rewards offering to set
     */
    public void setRewardsOffering(String rewardsOffering) {
        this.rewardsOffering = rewardsOffering;
    }

    /**
     * Converts the uploaded image to a byte array and stores it.
     *
     * @throws IOException if an error occurs during the conversion
     */
    public void convertImageToBytes() throws IOException {
        if (this.mainImage != null && !this.mainImage.isEmpty()) {
            this.image = this.mainImage.getBytes(); // Store the image as a byte array
        }
    }
}
