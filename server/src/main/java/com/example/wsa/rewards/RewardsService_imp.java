package com.example.wsa.rewards;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RewardsService_imp implements RewardsService {
    private final int FLAT_POINT_AMOUNT = 5;
    // ^ Temporary for use in add_points(). For variable rewards, make another function that determines this based
    // on the event's linked reward (need to change the schema first to allow rewards to reference events)
    private final RewardsRepo rewardsRepo;
    public RewardsService_imp(RewardsRepo theRewardsRepo) {
        this.rewardsRepo = theRewardsRepo;
    }

    @Override
    public void update_points(long volunteer_id) {
        // Add point allocations record (using only events registered) to the table after checking the following.
        //  a) Attempted allocation isn't already in table
        //  b) Event attached to reward attached to attempted allocation has expired
        List<Integer> events_registered_to_user = rewardsRepo.return_registered_events_list(volunteer_id);
        for (int event_id : events_registered_to_user) {
            // Here's where a conversion from event id to rewards id would go assuming they get linked up.
            if (
                !rewardsRepo.isAttemptedAllocationPresent(volunteer_id, event_id) // true if allocation not present (!false)
                    &&
                eventExpiryStatus(event_id) // returns true if event is expired.
            ) {
                rewardsRepo.add_points(volunteer_id, event_id, FLAT_POINT_AMOUNT);
            } else {
                System.out.println("update_points(): Attempted allocation skipped. Entry already present. ");
            }
        }
    }
    @Override
    public Integer retrieve_point_total(long volunteer_id) {
        return rewardsRepo.retrieve_point_total(volunteer_id);
    }

    @Override
    public List<String> retrieve_applicable_reward_names(int point_total) {
        List<RewardThreshold> allRewards = rewardsRepo.retrieve_all_reward_items();
        List<String> applicable_reward_names = new ArrayList<>();

        for (RewardThreshold reward : allRewards) {
            if (point_total >= reward.getPointsRequired()) {
                applicable_reward_names.add(reward.getImageName());
                System.out.println("retrieve_applicable_reward_names() added: " + reward.getImageName());
            }
        }
        return applicable_reward_names;
    }

    //-- Helper Methods --//
    private Boolean eventExpiryStatus(long event_id){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = formatEventExpiry(event_id);
        // Returns true if current date is after expiry date
        return currentDateTime.isAfter(expiryDateTime);
    }
    private LocalDateTime formatEventExpiry(long event_id){
        try {
            String eventExpiryDate = rewardsRepo.getEventExpiryDate(event_id);
            String eventExpiryTime = rewardsRepo.getEventExpiryTime(event_id);
            DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String eventExpiryDateTime = eventExpiryDate + " " + eventExpiryTime;
            // Needs to be a LocalDateTime object so that we can compare it above.
            return LocalDateTime.parse(eventExpiryDateTime, formatDateTime);

        } catch (Exception e) {
            System.out.println("formatEventExpiry(): Exception occurred");
            e.printStackTrace();
            return null;
        }
    }
}
