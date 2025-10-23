package com.kuganappa.wishlist.repository;

import com.kuganappa.wishlist.model.User;
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

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public final RowMapper<User> rowMapper = (rs, rowNum) ->
        new User(
                rs.getString("userName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getObject("dateOfBirth", LocalDate.class)
        );

    public List<User> getUsers(){
        return jdbc.query("SELECT * FROM users", rowMapper);
    }

    public void createUser(User user){
       KeyHolder keyHolder = new GeneratedKeyHolder();

       jdbc.update(connection -> {
           PreparedStatement ps = connection.prepareStatement(
                   "INSERT INTO users (userName, email, password, dateOfBirth)" +
                           " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
           );
           ps.setString(1,user.getUserName());
           ps.setString(2,user.getEmail());
           ps.setString(3,user.getPassword());
           ps.setObject(4,user.getDateOfBirth());
           return ps;
       }, keyHolder);

       Number id = keyHolder.getKey();
       if (id!=null){
           user.setUserId(id.intValue());
       }
    }

    public User getUserFromName(String userName) {
        List<User> list = jdbc.query(
                "SELECT * FROM users WHERE userName = ?", rowMapper, userName);
        return list.isEmpty() ? null : list.getFirst();
    }




}
