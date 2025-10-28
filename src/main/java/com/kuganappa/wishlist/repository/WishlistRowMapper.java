package com.kuganappa.wishlist.repository;

import com.kuganappa.wishlist.model.Wishlist;

import javax.swing.tree.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistRowMapper implements RowMapper<Wishlist> {
    @Override
    public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wishlist wishlist = new Wishlist();
        rs.getInt("wishlistId"),
                rs.getString("wishlistName"),
                rs.getString("ownerName"),
                rs.getString("description"),
        return wishlist;
    }
}
