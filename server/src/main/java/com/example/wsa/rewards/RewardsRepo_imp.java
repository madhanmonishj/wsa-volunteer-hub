package com.example.wsa.rewards;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RewardsRepo_imp implements RewardsRepo {

    private final JdbcTemplate jdbc;

    private final RowMapper<RewardThreshold> rewardThresholdMapper = (rs, rowNum) -> new RewardThreshold(
            rs.getLong("id"),
            rs.getString("imageName"),
            rs.getInt("pointsRequired")
    );

    public RewardsRepo_imp(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void add_points(long volunteer_id, long event_id, long amount) {
        // Adds to point_allocation table assuming checks below (called by service) pass.
        try {
            String sql = """
                    INSERT INTO point_allocations (volunteer_id, event_id, amount)
                    VALUES (?, ?, ?)
                    """;
            jdbc.update(sql, volunteer_id, event_id, amount);
        } catch (DataAccessException e) {
            System.out.println("add_points Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> return_registered_events_list(long volunteer_id){
        try {
            // Scans through volunteer_event table and returns every event id associated with this user.
            String sql = "SELECT event_id FROM volunteer_event WHERE volunteer_id = ?";
            return jdbc.queryForList(sql, Integer.class, volunteer_id);
        } catch (DataAccessException e) {
            System.out.println("return_registered_events_list Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean isAttemptedAllocationPresent(long volunteer_id, long event_id) {
        // Checks point allocation table and returns true if input arguments match entry already present.
        // Used in update_points() in service to avoid adding repeated entries with add_points() above.
        try {
            String sql = """
                    SELECT EXISTS (
                        SELECT 1 FROM point_allocations
                        WHERE volunteer_id=? AND event_id=?
                    ) AS entry_exists
                    """;
            return jdbc.queryForObject(sql, Boolean.class, volunteer_id, event_id);
        } catch (DataAccessException e) {
            System.out.println("isAttemptedAllocationPresent Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getEventExpiryDate(long event_id) {
        try {
            String sql = "SELECT date FROM event WHERE id = ?";
            // ^ Assumes all events last one day, so their date *is* their expiry.
            // This is stored as a DATE type field in the schema so I'll need to convert it to a string here.
            return jdbc.queryForObject(sql, new Object[]{event_id}, String.class);
        } catch (DataAccessException e) {
            System.out.println("getEventExpiryDate Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public String getEventExpiryTime(long event_id) {
        try {
            String sql = "SELECT end_time FROM event WHERE id = ?";
            // This is stored as a TIME type field in the schema so I'll need to convert it to a string here.
            return jdbc.queryForObject(sql, String.class, event_id);
        } catch (DataAccessException e) {
            System.out.println("getEventExpiryTime Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer retrieve_point_total(long volunteer_id) {
        // Sums all scores in point_allocation table with the volunteer's id, added by add_points()
        try {
            String sql = "SELECT SUM(amount) FROM point_allocations WHERE volunteer_id = ?";
            return jdbc.queryForObject(sql, Integer.class, volunteer_id);
        } catch (DataAccessException e) {
            System.out.println("retrieve point_total: Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RewardThreshold> retrieve_all_reward_items() {
        String sql = "SELECT * FROM reward_point_requirements";
        return jdbc.query(sql, rewardThresholdMapper);
    }
}

//    private final RowMapper<Reward> rewardRowMapper = (rs, rowNum) -> new Reward(
//            rs.getLong("id"),
//            rs.getInt("event_points"),
//            rs.getString("event_name"),
//            rs.getDate("date").toLocalDate()
//    );
//
//
//    @Override
//    public List<Reward> getAllRewards() {
//        String sql = " SELECT *, e.title AS event_name FROM reward r INNER JOIN volunteer_rewards vr ON vr.reward_id = r.id INNER JOIN volunteer_event ve ON ve.volunteer_id = vr.volunteer_id INNER JOIN event e ON e.id = ve.event_id;\n";
//        return jdbc.query(sql, rewardRowMapper);
//    }
//
//    @Override
//    public void addReward(Reward reward) {
//        String sql = "INSERT INTO reward (event_points, date) VALUES (?, ?)";
//        jdbc.update(sql, reward.getPoints(), reward.getDate());
//    }

