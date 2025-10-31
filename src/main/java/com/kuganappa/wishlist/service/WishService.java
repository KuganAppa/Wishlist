package com.kuganappa.wishlist.service;

import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {
    private final WishRepository wishRepository;

        public WishService(WishRepository wishRepository) {
            this.wishRepository = wishRepository;
        }

    public void createWish(Wish wish) {
            wishRepository.createWish(wish);
    }

    public List<Wish> getWishes() {
            return wishRepository.getWishes();
    }

    public Wish getWish(int wishId) {
            return wishRepository.getWishFromWishId(wishId);
    }
}
