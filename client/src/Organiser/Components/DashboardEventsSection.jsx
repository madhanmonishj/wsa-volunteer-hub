import React, { useState, useEffect } from "react";
import axios from "axios";
import EventsDashboardCard from "./EventsDashboardCard";
import { BASE_URL } from "../../apiConfig";

export default function DashboardEventsSection() {
  const [upcomingEvents, setUpcomingEvents] = useState([]);
  const [pastEvents, setPastEvents] = useState([]);
 const organiserID = 1;

  useEffect(() => {
    // Fetch upcoming events for the specific organiser
    axios
      .get(`${BASE_URL}/organiser-dashboard/events/upcoming/${organiserID}`)
      .then((response) => {
        setUpcomingEvents(response.data);
      })
      .catch((error) => {
        console.error("Error fetching upcoming events:", error);
      });

    // Fetch past events for the specific organiser
    axios
      .get(`${BASE_URL}/organiser-dashboard/events/past/${organiserID}`)
      .then((response) => {
        setPastEvents(response.data);
      })
      .catch((error) => {
        console.error("Error fetching past events:", error);
      });
  }, []);

  const defaultImage = "https://via.placeholder.com/400x300?text=Event+Image";

  return (
    <section className="py-12 bg-white poppins-regular">
      <div className="mx-auto max-w-screen-xl px-4 sm:px-6 lg:px-8">
        <div className="flex">
          <h2 className="text-4xl font-bold text-gray-900 sm:text-4xl">
            Upcoming Events
          </h2>
        </div>
        <div className="mt-2 grid gap-8 lg:grid-cols-3">
          {upcomingEvents?.map((event, index) => (
            <EventsDashboardCard
              key={index}
              imgSrc={event.eventImage || defaultImage} // Use Base64 string for image source
              title={event.name}
              description={event.description}
              status={event.approved ? "approved" : "pending"}
              eventId={event.id} // Pass the event ID to the card component
            />
          ))}
        </div>
      </div>

      <div className="mx-auto max-w-screen-xl px-4 sm:px-6 lg:px-8 mt-20">
        <div className="flex">
          <h2 className="text-4xl font-bold text-gray-900 sm:text-4xl">
            Past Events
          </h2>
        </div>

        <div className="mt-2 grid gap-8 lg:grid-cols-3">
          {pastEvents?.map((event, index) => (
            <EventsDashboardCard
              key={index}
              imgSrc={event.eventImage || defaultImage} // Use Base64 string for image source
              title={event.name}
              description={event.description}
              status={event.approved ? "approved" : "pending"}
              eventId={event.id} // Pass the event ID to the card component
            />
          ))}
        </div>
      </div>
    </section>
  );
}
