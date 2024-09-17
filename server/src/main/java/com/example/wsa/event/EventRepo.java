package com.example.wsa.event;

import com.example.wsa.event.exception.EventRepositoryException;
import com.example.wsa.event.exception.EventServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRepo {
    Page<EventDTO> getAllEvents(Pageable pageable);

    EventDTO getEvent(int id) throws EventRepositoryException, EventServiceException;

    EventDTO updateEventInfo(int id, EventDTO event) throws EventServiceException;


    EventDTO saveEvent(EventDTO event) throws EventServiceException;

    EventDTO getEventDetails(int id) throws EventServiceException;
}

