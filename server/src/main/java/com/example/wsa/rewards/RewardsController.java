package com.example.wsa.rewards;

import com.example.wsa.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.wsa.login.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/rewards")
@RestController
@RequestMapping("/rewards")
public class RewardsController {

    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping("/getPoints")
    public ResponseEntity<Integer> getPoints(@RequestParam Long userId) {
        try {
            rewardsService.update_points(userId);
            int points = rewardsService.retrieve_point_total(userId);
            return ResponseEntity.ok(points);
        } catch (Exception e) {
            e.printStackTrace(); // Log the stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getRewards")
    public ResponseEntity<List<String>> getApplicableRewards(@RequestParam int total_points) {
        try {
            List<String> applicableRewardNames = rewardsService.retrieve_applicable_reward_names(total_points);
            return ResponseEntity.ok(applicableRewardNames);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}


//@RequestMapping("/rewards")
//@Controller
//public class RewardsController {
//
//    private final RewardsService rewardsService;
//
//    public RewardsController(RewardsService rewardsService){
//        this.rewardsService = rewardsService;
//    }
//
//    @GetMapping("rewards/getPoints")
//    public Integer updateUserPointTotal(User user){
//        // We'll treat the user ID as a volunteer ID.
//        long volunteer_id = user.getId();
//        rewardsService.update_points(volunteer_id);
//        // for use with Rewards.jsx where this function is called.
//        return rewardsService.retrieve_point_total(volunteer_id);
//    }
//
//}
