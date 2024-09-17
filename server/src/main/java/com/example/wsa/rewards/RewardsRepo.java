package com.example.wsa.rewards;

import java.util.List;

public interface RewardsRepo {

    void add_points(long volunteer_id, long event_id, long points);
    public List<Integer> return_registered_events_list(long volunteer_id);

    Boolean isAttemptedAllocationPresent(long volunteer_id, long reward_id);
    String getEventExpiryDate(long event_id);
    String getEventExpiryTime(long event_id);

    Integer retrieve_point_total(long volunteer_id);
    // ^ Used by service with method below to safely add a point allocation when user logs in to their page.
    List<RewardThreshold> retrieve_all_reward_items();
}
