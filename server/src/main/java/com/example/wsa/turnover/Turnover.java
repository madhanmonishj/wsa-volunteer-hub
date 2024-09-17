package com.example.wsa.turnover;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "turnover")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Turnover {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String types;
}