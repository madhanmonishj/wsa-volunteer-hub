import React, { useState } from "react";
import VolunteerHeader from "../Header/VolunteerHeader";
import VolunteerFooter from "../Footer/VolunteerFooter";
import VolunteeerInfo from "./VolunteerProfileInfoTemplate";

const VolunteerInfo = () => {
  const [updated, setUpdated] = useState(false);
  const handleUpdate = () => {
    setUpdated(true);
  };
  return (
    <>
      <VolunteerHeader logged={true} updated={updated} />
      <VolunteeerInfo editable={true} onUpdate={handleUpdate} />
      <VolunteerFooter />
    </>
  );
};

export default VolunteerInfo;
