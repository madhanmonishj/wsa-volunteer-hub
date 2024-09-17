import React, { useState } from "react";
import { QuickRegisterModal } from "./QuickRegisterModal";

const EventCard = ({ imgSrc, title, date, description }) => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <article className="overflow-hidden rounded-lg border border-gray-100 shadow-sm">
      {/* Event image */}
      <img alt="Event" src={imgSrc} className="h-56 w-full object-cover" />

      {/* Event details */}
      <div className="p-4 sm:p-6 text-left">
        {/* Event title */}
        <a href="#">
          <h3 className="text-lg font-medium text-gray-900">{title}</h3>
        </a>
        {/* Event date */}
        <p className="mt-1 text-sm text-gray-500">Date: {date}</p>
        {/* Event description */}
        <p className="mt-2 line-clamp-3 text-sm text-gray-500">{description}</p>
        {/* Link to find out more about the event */}
        <button type="button"
          href="#"
          className="group mt-4 inline-flex items-center gap-1 text-sm font-medium text-[#CC1B00]"
          onClick={handleOpen}
        >
          Find out more
          <span className="block transition-all group-hover:ms-0.5 rtl:rotate-180">
            &rarr; {/* Arrow icon on button */}
          </span>
        </button>
        <QuickRegisterModal open={open} handleClose={handleClose} title={title} description={description}/>
      </div>
    </article>
  );
};

export default EventCard;
