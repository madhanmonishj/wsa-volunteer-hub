package com.example.wsa.quickRegister;

import lombok.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class QuickRegisterDTO {
    private Integer id;
    private Integer eventID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dob;

    public QuickRegisterDTO(Integer eventID, String firstName, String lastName, String email, String phoneNumber) {
        this.eventID = eventID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
