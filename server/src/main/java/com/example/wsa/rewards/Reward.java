package com.example.wsa.rewards;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
    private Long id;
    private int points;
    private String event;
    private LocalDate date;
}
