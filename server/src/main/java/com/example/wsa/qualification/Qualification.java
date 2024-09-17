package com.example.wsa.qualification;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "qualifications")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private QualificationType type;
}
