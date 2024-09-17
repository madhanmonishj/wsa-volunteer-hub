package com.example.wsa.occupation;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "occupations")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String category;
}
