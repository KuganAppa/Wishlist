package com.kuganappa.wishlist.controller;

import com.kuganappa.wishlist.model.User;
import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.model.Wishlist;
import com.kuganappa.wishlist.service.UserService;
import com.kuganappa.wishlist.service.WishService;
import com.kuganappa.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("mypage/{wishlistId}")
    public String showSpecificWishlist(@PathVariable int wishlistId, Model model){
        model.addAttribute("wishlist",wishlistService.getSpecificWishlist(wishlistId));
        return "myWishlistPage";
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