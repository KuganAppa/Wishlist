package com.kuganappa.wishlist.repository.rowMappers;

import com.kuganappa.wishlist.model.Wish;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {

    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wish wish = new Wish();
        wish.setWishId(rs.getInt("wishId"));
        wish.setWishName(rs.getString("wishName"));
        wish.setDescription(rs.getString("description"));
        wish.setPrice(rs.getDouble("price"));
        wish.setPictureLink(rs.getString("pictureLink"));
        wish.setPurchaseLink(rs.getString("purchaseLink"));
        return wish;
    }
}