package com.example.wsa.rewards;

import jakarta.persistence.*;
import java.time.LocalDate;
/**
 * Organiser Model Class
 * created @author Enoch Ribin 23089855
 */
@Entity
@Table(name = "reward")
public class Rewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private String image;

    private String description;
}
