package com.kuganappa.wishlist.repository;
import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.model.Wishlist;
import com.kuganappa.wishlist.repository.rowMappers.WishRowMapper;
import com.kuganappa.wishlist.repository.rowMappers.WishlistRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbc;

    private final WishRowMapper wishRowMapper = new WishRowMapper();
    private final WishlistRowMapper wishlistRowMapper = new WishlistRowMapper(this);

    public WishlistRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void createWishlist(Wishlist wishlist) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO wishlist (wishlistName, owner, description)" +
                            " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, wishlist.getWishlistName());
            ps.setObject(2, wishlist.getOwner().getUserName());
            ps.setString(3, wishlist.getDescription());
            return ps;
        }, keyHolder);

        Number id = keyHolder.getKey();
        if (id != null) {
            wishlist.setWishlistId(id.intValue());
        }
    }

    public void addWishToWishlist(int wishlistId, int wishId) {
        String sql = "INSERT INTO wishlist_wishes (wishlistId, wishId) VALUES (?, ?)";
        jdbc.update(sql, wishlistId, wishId);
    }

    public List<Wish> getWishesFromWishlist(Integer wishlistId) {
        String sql = """
            SELECT w.*
            FROM wish w
            JOIN wishlist_wishes ww ON w.wishId = ww.wishId
            WHERE ww.wishlistId = ?
        """;
        return jdbc.query(sql, wishRowMapper, wishlistId);
    }

    public Wishlist getSpecificWishlist(int wishlistId){
        String sql ="""
                SELECT *
                from wishlist
                WHERE wishlist.wishlistId = ?
                """;
        List <Wishlist> wishlist = jdbc.query(sql,wishlistRowMapper,wishlistId);
        return wishlist.isEmpty() ? null : wishlist.get(0);
    }

    public List<Wishlist> getAllWishlists() {
        String sql = "SELECT * FROM wishlist";
        return jdbc.query(sql, wishlistRowMapper);
    }

    public List<Wishlist> allWishlistsForUser(int userId){
        String sql = "SELECT * FROM wishlist WHERE userId = ?";
        return jdbc.query(sql,wishlistRowMapper,userId);
    }
}