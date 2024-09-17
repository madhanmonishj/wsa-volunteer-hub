package com.example.wsa.accessibilty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accessibility_enhancement")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Accessibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
