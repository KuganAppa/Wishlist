package com.kuganappa.wishlist.controller;

import com.kuganappa.wishlist.model.User;
import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.model.Wishlist;
import com.kuganappa.wishlist.service.UserService;
import com.kuganappa.wishlist.service.WishService;
import com.kuganappa.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;

@Controller
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;
    private final WishService wishService;

    public WishlistController(WishlistService wishlistService, UserService userService, WishService wishService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
        this.wishService = wishService;
    }

    public void createUser (User user){
        userService.createUser(user);
    }

    public void createWish(Wish wish){
        wishService.createWish(wish);
    }

    public void createWishlist(Wishlist wishlist){
        wishlistService.createWishlist(wishlist);
    }
}