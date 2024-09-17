package com.example.wsa.volunteer_signup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SignUpRepo {
    void saveVolunteer(Volunteer volunteer);
    void addUserEntry(Volunteer volunteer);
//    Page<Volunteer> getAllVolunteers(Pageable pageable);
}
