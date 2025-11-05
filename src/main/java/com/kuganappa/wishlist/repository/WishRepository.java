package com.kuganappa.wishlist.repository;

import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.repository.rowMappers.WishRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbc;
    private final WishRowMapper wishRowMapper = new WishRowMapper();

    public WishRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int createWish(Wish wish) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO wish (wishName, description, price, pictureLink, purchaseLink) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, wish.getWishName());
            ps.setString(2, wish.getDescription());
            ps.setDouble(3, wish.getPrice());
            ps.setString(4, wish.getPictureLink());
            ps.setString(5, wish.getPurchaseLink());
            return ps;
        }, keyHolder);

        Number id = keyHolder.getKey();
        if (id != null) {
            wish.setWishId(id.intValue());
            return id.intValue();
        }
        throw new RuntimeException("Failed to generate ID for wish");
    }

    public List<Wish> getAllWishes() {
        return jdbc.query("SELECT * FROM wish", wishRowMapper);
    }

    public Wish getWishFromName(String wishName) {
        List<Wish> wishes = jdbc.query("SELECT * FROM wish WHERE wishName = ?", wishRowMapper, wishName);
        return wishes.isEmpty() ? null : wishes.getFirst();
    }


    public Wish getWishFromWishId(int wishId) {
        List<Wish> list = jdbc.query(
                "SELECT * FROM wish WHERE wishId = ?", wishRowMapper, wishId);
        return list.isEmpty() ? null : list.getFirst();
    }

    public void deleteWish(String wishName) {
        jdbc.update("DELETE FROM wish WHERE wishName = ?", wishName);
    }

    public void updateWish(Wish wish) {
        jdbc.update("UPDATE wish SET description = ?, price = ?, pictureLink = ?, purchaseLink = ? WHERE wishName = ?",
                wish.getDescription(),
                wish.getPrice(),
                wish.getPictureLink(),
                wish.getPurchaseLink(),
                wish.getWishName()
        );
    }
}