package com.example.wsa.volunteer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Volunteer Repository Interface
 */
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer,Integer> {

    /**
     * Stored Procudure to return only volunteer image using volunteer ID
     * @param volunteerID
     * @return byte[]
     */
    @Procedure(procedureName = "GetVolunteerProfilePicture")
    byte[] getVolunteerProfilePicture(@Param("volunteer_id") Integer volunteerID);

    /**
     * Stored Procedure to get a paginated list of volunteers
     * 
     * @param page
     * @param size
     * @return List of volunteers as Object arrays
     */
    @Procedure(procedureName = "getPageVolunteer")
    List<Object[]> getPageVolunteer(@Param("page") Integer page, @Param("size") Integer size);
}
