package com.kuganappa.wishlist.controller;

import com.kuganappa.wishlist.model.User;
import com.kuganappa.wishlist.model.Wish;
import com.kuganappa.wishlist.model.Wishlist;
import com.kuganappa.wishlist.service.UserService;
import com.kuganappa.wishlist.service.WishService;
import com.kuganappa.wishlist.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("wishy")
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;
    private final WishService wishService;

    public WishlistController(WishlistService wishlistService, UserService userService, WishService wishService) {
        this.wishlistService = wishlistService;
        this.userService = userService;
        this.wishService = wishService;
    }

    @GetMapping("")
    public String entryPage() {
        return "login";
    }

    // Login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.getUserFromName(username);

        if (user == null) {
            model.addAttribute("error", "Brugernavn findes ikke");
            return "login";
        }

        if (password.equals(user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/wishy/homepage/" + user.getUserId();
        } else {
            model.addAttribute("error", "Forkert adgangskode");
            return "login";
        }
    }

    // Homepage
    @GetMapping("/homepage/{userId}")
    public String homepage(@PathVariable int userId, Model model) {
        model.addAttribute("user", userService.getUser(userId));
        return "homepage";
    }

    // List of wishlist
    @GetMapping("{userId}/wishlists")
    public String showAllWishlist(@PathVariable int userId, Model model) {
        model.addAttribute("usersLists", wishlistService.allWishlistsForUser(userId));
        return "showAllWishlistsForUser";
    }

    // Specific wishlist
    @GetMapping("/wishlists/{wishlistId}")
    public String showSpecificWishlist(@PathVariable int wishlistId, Model model) {
        model.addAttribute("wishlist", wishlistService.getSpecificWishlist(wishlistId));
        return "showSpecificWishlist";
    }

    // List of users
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "showUsers";
    }

    // Specific user
    @GetMapping("/users/{userId}")
    public String showSpecificUser(@PathVariable int userId, Model model) {
        model.addAttribute("user", userService.getUser(userId));
        return "showSpecificUser";
    }

    // List of wishes
    @GetMapping("/wishes")
    public String showWishes(Model model) {
        model.addAttribute("wishes", wishService.getWishes());
        return "showWishes";
    }

    // Specific wish
    @GetMapping("/wishes/{wishId}")
    public String showWish(@PathVariable int wishId, Model model) {
        model.addAttribute("wish", wishService.getWish(wishId));
        return "showSpecificWish";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/wishy/users";
    }

    @PostMapping("/wishes")
    public String createWish(Wish wish) {
        wishService.createWish(wish);
        return "redirect:/wishy/wishes";
    }

    @PostMapping("/wishlist")
    public String createWishlist(Wishlist wishlist) {
        wishlistService.createWishlist(wishlist);
        return "redirect:/wishy/wishlists";
    }
}