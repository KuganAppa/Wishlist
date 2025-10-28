package com.kuganappa.wishlist.repository;

import com.kuganappa.wishlist.model.Wish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WishRepositoryIntegrationTest {

    @Autowired
    private WishRepository wishRepository;

    @Test
    void createWish_shouldInsertIntoDatabase() {
        Wish wish = new Wish("iPhone 16", "Apple iPhone 16", 100.0, "linktilbillede.com", "linktilprodukt.com");
        wishRepository.createWish(wish);

        Wish saved = wishRepository.getWishFromName("iPhone 16");
        assertEquals("x", saved.getWishName());
    }

}
