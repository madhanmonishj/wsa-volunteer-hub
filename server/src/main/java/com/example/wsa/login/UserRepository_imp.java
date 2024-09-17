package com.example.wsa.login;

import com.example.wsa.rewards.Reward;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository_imp implements UserRepository {

    private final JdbcTemplate jdbc;
    private RowMapper<User> userItemMapper;

    public UserRepository_imp(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setUserItemMapper();
    }

    private void setUserItemMapper() {
        userItemMapper = (rs, i) -> new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password")
        );
    }

    @Override
    public List<User> findAll() {
        // SQL statement to return all users from the user table using the mapper.
        String sql = "SELECT * FROM user";
        return jdbc.query(sql, userItemMapper);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        return jdbc.queryForObject(sql, userItemMapper, username);
    }
}
