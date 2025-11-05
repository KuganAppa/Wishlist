package com.kuganappa.wishlist.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class WishlistTest {

    @Test
    void testWishlistConstructorAndGetters() {
        User user = new User();
        Wish wish = new Wish("Book", "Java 101", 199.0, null, null);
        Wishlist wishlist = new Wishlist(1, "Christmas wishlist", user, "For Christmas", List.of(wish));

        assertEquals(1, wishlist.getWishlistId());
        assertEquals("Christmas wishlist", wishlist.getWishlistName());
        assertEquals(2665, wishlist.getOwner());
        assertEquals("For Christmas", wishlist.getDescription());
        assertEquals(wish, wishlist.getWishes().getFirst());
    }
}
