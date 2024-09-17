package com.example.wsa.quickRegister;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import jakarta.persistence.ParameterMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuickRegisterServiceImpl implements QuickRegisterService {

    private final List<QuickRegisterDTO> volunteerList = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;  // For interacting with the database

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ResponseEntity<String> quickRegisterVolunteer(QuickRegisterDTO quickRegisterDTO) {

        // Create a stored procedure query
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("QuickRegisterVolunteer");

        // Register parameters (adjust the parameter types and positions according to your stored procedure)
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN); // Event ID
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);  // First name
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);  // Last name
        query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);  // Email
        query.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);  // Phone number

        // Set the parameters
        query.setParameter(1, quickRegisterDTO.getEventID());
        query.setParameter(2, quickRegisterDTO.getFirstName());
        query.setParameter(3, quickRegisterDTO.getLastName());
        query.setParameter(4, quickRegisterDTO.getEmail());
        query.setParameter(5, quickRegisterDTO.getPhoneNumber());

        // Execute the stored procedure
        query.execute();

        // Return success message
        return ResponseEntity.ok("Volunteer signed up successfully");
    }
}
