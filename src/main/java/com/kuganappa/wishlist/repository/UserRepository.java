package com.kuganappa.wishlist.repository;

import com.kuganappa.wishlist.model.User;
import com.kuganappa.wishlist.repository.rowMappers.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;
    private final UserRowMapper rowMapper = new UserRowMapper();

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> getAllUsers() {
        return jdbc.query("SELECT * FROM users",rowMapper);
    }

    public void createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO users (userName, email, password, dateOfBirth)" +
                            " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setObject(4, user.getDateOfBirth());
            return ps;
        }, keyHolder);

        Number id = keyHolder.getKey();

        if (id != null) {
            user.setUserId(id.intValue());
        }
    }

    public User getUserFromName(String userName) {
        List<User> list = jdbc.query(
                "SELECT * FROM users WHERE userName = ?", rowMapper, userName);
        return list.isEmpty() ? null : list.get(0);
    }

    public User getUserFromId(int userId) {
        List<User> list = jdbc.query(
                "SELECT * FROM users WHERE userId = ?", rowMapper, userId);
        return list.isEmpty() ? null : list.get(0);
    }

    public User getUserFromEmail(String email) {
        List<User> list = jdbc.query(
                "SELECT * FROM users WHERE email = ?", rowMapper, email);
        return list.isEmpty() ? null : list.get(0);
    }
}