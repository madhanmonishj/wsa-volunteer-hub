import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import { EventCard } from '../Admin/Events/EventCard';
import { format } from 'date-fns';

describe('EventCard Component', () => {
    const mockOnPageChange = jest.fn();
    const event = { id: 1, name: 'Event 1', date: '2024-08-01', time: '10:00 AM' };
    const formattedDate = format(new Date(event.date), 'dd MMM').split(' ');
        render(
            <EventCard
                date={`${formattedDate[0]} ${formattedDate[1]}`}
                name={event.name}
                time={event.time}
                onPageChange={mockOnPageChange}
                event={event}
            />
        )
});
