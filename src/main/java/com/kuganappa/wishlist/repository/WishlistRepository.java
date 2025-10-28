package com.kuganappa.wishlist.repository;
import com.kuganappa.wishlist.repository.WishRowMapper;
import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.model.Wishlist;
import org.springframework.jdbc.core.RowMapper;
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

    public WishlistRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Wishlist> rowMapper = (rs, rowNum) ->
            new Wishlist(
                    rs.getInt("wishlistId"),
                    rs.getString("wishlistName"),
                    rs.getString("ownerName"),
                    rs.getString("description")
                    //List<Wish> wishes = ""//getWishesForWishlist(rs.getInt("id"))
            );

    public void createWishlist(Wishlist wishlist){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO wishlist (wishlistName, ownerId, description, wishes)" +
                            " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, wishlist.getWishlistName());
            ps.setInt(2,wishlist.getOwnerId());
            ps.setString(3,wishlist.getDescription());
            ps.setObject(4,wishlist.getWishes());
            return ps;
        }, keyHolder);

        Number id = keyHolder.getKey();
        if (id!=null){
            wishlist.setWishlistId(id.intValue());
        }
    }

    public List<Wishlist> getWishlists(){
        return jdbc.query("SELECT * FROM wishlist", rowMapper);
    }

    public void addWishToWishlist(int wishlistId, int wishId){
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO wishlist_wishes (wishlistId, wishId)" + "VALUES (?, ?)"
            );
            ps.setInt(1, wishlistId);
            ps.setInt(2, wishId);
            return ps;
        });
    }

    public List<Wish> getWishesFromWishlist(Integer wishlistId) {
        String sql = """
        SELECT w.*
        FROM wish w
        JOIN wishlist_wishes ww ON w.wishId = ww.wishId
        WHERE ww.wishlistId = ?
    """;
        return jdbc.query(sql, new WishRowMapper(), wishlistId);
    }


}