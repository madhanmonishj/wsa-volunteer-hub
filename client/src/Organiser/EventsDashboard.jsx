import React, {useEffect, useState} from "react";
import SideMenu from "./Components/SideMenu"; // Importing SideMenu component
import DashboardEventsSection from "./Components/DashboardEventsSection";
import { useAuth0 } from "@auth0/auth0-react";
import { useOrganiser } from "../Context/Organiser";
import axios from "axios";
import { BASE_URL } from "../apiConfig";
import AddEvent from "./AddEvent";
import OrganiserInfoTemplate from "./OrganiserInfoTemplate";

export default function EventsDashboard() {
  const containerClass = "flex h-screen";
  const { user, isAuthenticated, getAccessTokenSilently } = useAuth0();
  const { OrganiserID , updateOrganiserID } = useOrganiser();
  const [updated, setUpdated] = useState(false);
  const handleUpdate = () => {
    setUpdated(true);
  };
  const [activeView, setActiveView] = useState("dashboard"); // State to track the active view

  useEffect(() => {
    const fetchToken = async () => {
      const domain = "dev-8c2xr3hvid8jvodp.uk.auth0.com";

      if (isAuthenticated) {
        try {
          const currentUserAccessToken = await getAccessTokenSilently({
            authorizationParams: {
              audience: `https://${domain}/api/v2/`,
              scope: "read:current_user",
            },
          });
  
          const userDetailsByIdUrl = `https://${domain}/api/v2/users/${user.sub}`;
          const metadataResponse = await fetch(userDetailsByIdUrl, {
            headers: {
              Authorization: `Bearer ${currentUserAccessToken}`,
            },
          });
          
          const { user_metadata, email } = await metadataResponse.json();

          console.log(user_metadata);
          const { 
            post_code,
            address,
            telephone,
            website,
            founding_date,
            associated_clubs,
            number_of_members
          } = user_metadata || {};

            const organiser = {
              post_code,
              address_link: address,
              telephone_no: telephone,
              website_link : website,
              founding_date,
              associated_clubs,
              number_of_members,
              email_id: email
            };

            const saveResponse = await axios.post(`${BASE_URL}/api/organiser/saveOrganiser`, organiser);
            updateOrganiserID(saveResponse.data);
        } catch (error) {
          console.error("Error fetching token:", error);
        }
      }
    };
  
    fetchToken();
  }, [user?.sub, getAccessTokenSilently]);

  console.log(OrganiserID);
  const renderView = () => {
    switch (activeView) {
      case "dashboard":
        return <DashboardEventsSection />;
      case "addEvent":
        return <AddEvent setActiveView={setActiveView} />;
      case "organiserInfo":
        return (
          <OrganiserInfoTemplate editable={true} onUpdate={handleUpdate} />
        );
      default:
        return <DashboardEventsSection />;
    }
  };

  return (
    <div className={containerClass}>
      <SideMenu setActiveView={setActiveView} updated={updated} />
      <div className="flex-grow sm:ml-64">{renderView()}</div>
    </div>
  );
}