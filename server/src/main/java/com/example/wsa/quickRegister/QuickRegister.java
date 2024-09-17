package com.example.wsa.quickRegister;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "temporary_volunteers")
@Setter
@Getter
@ToString
public class QuickRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer eventID;

    private String firstName;

    private String lastName;
    
    private String email;

    private String phoneNumber;
}