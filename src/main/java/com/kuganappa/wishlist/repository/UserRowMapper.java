package com.kuganappa.wishlist.repository;

import com.kuganappa.wishlist.model.User;
import com.mysql.cj.protocol.Resultset;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user =  new User();
        user.setUserId(rs.getInt("userId"));
        user.setUserName(rs.getString("userName"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setDateOfBirth((LocalDate) rs.getObject("dateOfBirth"));

        return user;
    }
}
