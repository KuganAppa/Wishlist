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

import java.util.List;

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

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html i templates-mappen
    }


    // Login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        User user = userService.getUserFromName(username);
        if (user != null && password.equals(user.getPassword())) {
            session.setAttribute("user", user);
            int userId = user.getUserId();
            return "redirect:/wishy/homepage/" + userId;
        } else {
            return "redirect:/wishy/login?error=true";
        }
    }

    // Vis opret bruger side
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // register.html
    }

    // POST: opret ny bruger
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                               @RequestParam String confirmPassword,
                               Model model) {

        if (userService.usernameExists(user.getUserName())) {
            model.addAttribute("error", "Brugernavnet findes allerede!");
            return "register";
        }

        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Brugernavnet findes allerede!");
            return "register";
        }

        userService.createUser(user);
        return "redirect:/wishy/login"; // redirect til login efter oprettelse
    }

    @GetMapping("/homepage/{userId}")
    public String showHomepage(@PathVariable int userId, Model model) {
        User user = userService.getUserFromId(userId); // hent bruger
        if (user == null) {
            return "redirect:/wishy/login"; // hvis user ikke findes
        }
        model.addAttribute("user", user);

        // Hvis du vil vise ønskelister:
        List<Wishlist> usersLists = wishlistService.allWishlistsForUser(userId);
        model.addAttribute("usersLists", usersLists);

        return "homepage"; // homepage.html
    }



    @GetMapping("{userId}/wishlists")
    public String showAllWishlist(@PathVariable int userId, Model model){
        model.addAttribute("user", userService.getUserFromId(userId));
        model.addAttribute("userWishlists", wishlistService.allWishlistsForUser(userId));
        return "showAllWishlistsForUser";
    }


    @GetMapping("/wishlists/{wishlistId}")
    public String showSpecificWishlist(@PathVariable int wishlistId, Model model){
        Wishlist wishlist = wishlistService.getSpecificWishlist(wishlistId);
        // hent wishes fra databasen
        wishlist.setWishes(wishlistService.getWishesFromWishlist(wishlistId));
        model.addAttribute("wishlist", wishlist);
        return "showSpecificWishlist";
    }

    // List of users
    @GetMapping("/users")
    public String showAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "showUsers";
    }


    // Specific user
    @GetMapping("/users/{userId}")
    public String showSpecificUser(@PathVariable int userId, Model model){
        User user = userService.getUserFromId(userId);
        model.addAttribute("user", user);
        model.addAttribute("usersLists", wishlistService.allWishlistsForUser(userId));
        return "showSpecificUser";
    }


    // List of wishes
    @GetMapping("/wishes")
    public String showWishes(Model model){
        model.addAttribute("wishes", wishService.getWishes());
        return "showWishes";
    }


    // Specific wish
    @GetMapping("/wishes/{wishId}")
    public String showWish(@PathVariable int wishId, Model model){
        model.addAttribute("wish", wishService.getWish(wishId));
        return "showSpecificWish";
    }


    //add wish to wishlist

    @PostMapping("/users")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/wishy/users";
    }

    // Åbner siden til at oprette en ny ønskeliste
    @GetMapping("/createWishlist")
    public String showCreateWishlistForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/wishy/login";
        }
        model.addAttribute("user", user);
        return "createWishlist";
    }

    // Opretter selve ønskelisten
    @PostMapping("/createWishlist")
    public String createWishlist(@RequestParam String wishlistName,
                                 @RequestParam String description,
                                 HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/wishy/login";
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistName(wishlistName);
        wishlist.setDescription(description);
        wishlist.setOwner(user);

        wishlistService.createWishlist(wishlist);
        return "redirect:/wishy/homepage/" + user.getUserId();
    }


    // Åbner siden til at oprette et nyt ønske
    @GetMapping("/createWish")
    public String showCreateWishForm(Model model) {
        //model.addAttribute("wishlistId", wishlistId);
        return "createWish"; // Thymeleaf template
    }




    @PostMapping("/createWish")
    public String createWish(@RequestParam String wishName,
                             @RequestParam String description,
                             @RequestParam double price,
                             @RequestParam(required = false) String pictureLink,
                             @RequestParam(required = false) String purchaseLink,
                             @RequestParam int wishlistId) {

        Wish wish = new Wish();
        wish.setWishName(wishName);
        wish.setDescription(description);
        wish.setPrice(price);
        wish.setPictureLink(pictureLink);
        wish.setPurchaseLink(purchaseLink);

        int wishId = wish.getWishId(); // returner ID fra repository
        wishlistService.addWishToWishlist(wishId, wishlistId);

        return "redirect:/wishy/wishlists/" + wishlistId;
    }




}