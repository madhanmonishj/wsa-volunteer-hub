package com.example.wsa.utility;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic class to remove not null values in response
 */
public class NotNullResponse <T>{
    private final T data;
    private final Map<String, Object> notNullFields = new HashMap<>();

    public NotNullResponse(T data) {
        this.data = data;
        populateNotNullFields();
    }

    /**
     * Method to access all the fields of the object passed and populate the map<String, Object> if not null
     */
    private void populateNotNullFields() {
        for (var field : data.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(data);
                if (value != null) {
                    notNullFields.put(field.getName(), value);
                }
            } catch (IllegalAccessException e) {
                System.out.println("Error");
            }
        }
    }

    @JsonAnyGetter
    public Map<String, Object> getNonNullFields() {
        return notNullFields;
    }
}
