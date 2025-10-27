package com.kuganappa.wishlist.repository;

import com.kuganappa.wishlist.model.User;
import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.model.Wishlist;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class WishlistRepository {
    private final JdbcTemplate jdbc;

    public WishlistRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

//    private final RowMapper<Wishlist> rowMapper = (rs, rowNum) ->
//            new Wishlist(
//                    rs.getInt("wishlistId"),
//                    rs.getString("wishlistName"),
//                    rs.getString("ownerName"),
//                    rs.getString("description"),
//                    getWishesForWishlist(rs.getInt("id"))
//            );


//    private List<Wish> getWishesForWishlist(int wishlistId) {
//        return jdbc.query("SELECT t.name FROM tag t JOIN attraction_tag at ON t.id = at.tag_id WHERE at.attraction_id = ?",
//                (rs, rowNum) -> Tag.valueOf(rs.getString("name")), attractionId);
//    }
//    }
}