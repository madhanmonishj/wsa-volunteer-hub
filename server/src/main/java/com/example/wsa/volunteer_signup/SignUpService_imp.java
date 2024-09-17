package com.example.wsa.volunteer_signup;

import org.springframework.stereotype.Service;

@Service
public class SignUpService_imp implements SignUpService {

    private final SignUpRepo signUpRepo;

    public SignUpService_imp(SignUpRepo aSignUpRepo){
        this.signUpRepo = aSignUpRepo;
    }

    @Override
    public void saveVolunteer(Volunteer volunteer) {
        signUpRepo.saveVolunteer(volunteer);
    }

    @Override
    public void addUserEntry(Volunteer volunteer) {
        signUpRepo.addUserEntry(volunteer);
    }
}
