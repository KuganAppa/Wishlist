package com.kuganappa.wishlist.service;

import com.kuganappa.wishlist.model.Wishlist;
import com.kuganappa.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void createWishlist(Wishlist wishlist) {
        wishlistRepository.createWishlist(wishlist);
    }

    public void addWishToWishlist(int wishId, int wishlistID) {
        wishlistRepository.addWishToWishlist(wishId, wishlistID);
    }

    public Wishlist getSpecificWishlist(int wishlistId) {
        return wishlistRepository.getSpecificWishlist(wishlistId);
    }

    public List<Wishlist> allWishlistsForUser(int userId) {
        return wishlistRepository.allWishlistsForUser(userId);
    }
}