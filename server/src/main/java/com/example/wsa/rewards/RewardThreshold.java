package com.example.wsa.rewards;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardThreshold {
    // Stores the name of the png associated with reward and the points required for it to be shown on rewards page
    private Long id;
    private String imageName;
    private int pointsRequired;
}
