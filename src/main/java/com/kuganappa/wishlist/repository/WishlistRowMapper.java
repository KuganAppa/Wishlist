package com.kuganappa.wishlist.repository;

import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.model.Wishlist;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WishlistRowMapper implements RowMapper<Wishlist> {

    private final WishlistRepository wishlistRepository;

    public WishlistRowMapper(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        int wishlistId = rs.getInt("wishlistId");

        List<Wish> wishes = wishlistRepository.getWishesFromWishlist(wishlistId);

        return new Wishlist(
                wishlistId,
                rs.getString("wishlistName"),
                rs.getInt("ownerId"),
                rs.getString("description"),
                wishes
        );
    }
}
