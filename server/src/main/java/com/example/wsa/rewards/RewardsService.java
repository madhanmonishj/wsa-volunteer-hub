package com.example.wsa.rewards;
import java.util.List;
public interface RewardsService {
    void update_points(long volunteer_id);
    Integer retrieve_point_total(long volunteer_id);
    List<String> retrieve_applicable_reward_names(int point_total);

}
