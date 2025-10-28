package com.kuganappa.wishlist.service;

import com.kuganappa.wishlist.repository.WishRepository;

public class WishService {
    private final WishRepository wishRepository;

        public WishService(WishRepository wishRepository) {
            this.wishRepository = wishRepository;
        }
}
