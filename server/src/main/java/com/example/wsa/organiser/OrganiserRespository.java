package com.example.wsa.organiser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Organiser Repository Interface
 */
public interface OrganiserRespository extends JpaRepository<Organiser, Integer> {
    /**
     * Stored Procedure to return only organiser image using organiser ID
     *
     * @param organiserID organiser ID
     * @return byte[]
     */
    @Procedure(procedureName = "GetOrganiserProfilePicture")
    byte[] getOrganiserProfilePicture(@Param("organiser_id") Integer organiserID);

    /**
     * Stored Procedure to get a paginated list of organisers
     *
     * @param page
     * @param size
     * @return List of volunteers as Object arrays
     */
    @Procedure(procedureName = "getPageOrganiser")
    List<Object[]> getPageOrganiser(@Param("page") Integer page, @Param("size") Integer size);
}
