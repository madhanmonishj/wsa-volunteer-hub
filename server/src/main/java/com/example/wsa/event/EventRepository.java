package com.example.wsa.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Event Repository Interface
 */
@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {

    /**
     * Fetch Upcoming Approved Events using stored procedure
     * @return List<Objects[]>
     */
    @Query(value = "CALL GetUpcomingEvents()", nativeQuery = true)
    List<Object[]> findUpcomingEvents();

    /**
     * Fetch Approved Events Dates using stored procedure
     * @return List<Objects[]>
     */
    @Query(value = "CALL GetApprovedEventsDates()", nativeQuery = true)
    List<Object[]> findApprovedEventsDates();

    /**
     * Fetch Past Approved Events Dates using stored procedure
     * @return List<Object[]>
     */
    @Query(value = "CALL GetPastEvents()", nativeQuery = true)
    List<Object[]> getPastEvents();

    @Procedure(procedureName = "GetEventDetailsByEventID")
    List<Object[]> getEventDetailsByEventID(@Param("event_id") Integer eventId);

}
