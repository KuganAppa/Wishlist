package com.kuganappa.wishlist.repository.rowMappers;

import com.kuganappa.wishlist.model.User;
import com.kuganappa.wishlist.model.Wishlist;
import com.kuganappa.wishlist.repository.WishlistRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistRowMapper implements RowMapper<Wishlist> {

    private final WishlistRepository wishlistRepository;

    public WishlistRowMapper(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistId(rs.getInt("wishlistId"));
        wishlist.setWishlistName(rs.getString("wishlistName"));
        wishlist.setDescription(rs.getString("description"));

        // Opret User objekt ud fra ownerId
        int ownerId = rs.getInt("ownerId");
        User owner = new User();
        owner.setUserId(ownerId);
        // evt. sæt flere felter hvis du har dem i tabellen
        wishlist.setOwner(owner);

        return wishlist;
    };
}
