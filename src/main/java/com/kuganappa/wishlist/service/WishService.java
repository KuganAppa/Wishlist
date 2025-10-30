package com.kuganappa.wishlist.service;

import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.repository.WishRepository;
import org.springframework.stereotype.Service;

@Service
public class WishService {
    private final WishRepository wishRepository;

        public WishService(WishRepository wishRepository) {
            this.wishRepository = wishRepository;
        }

    public void createWish(Wish wish) {
            wishRepository.createWish(wish);
    }
}
