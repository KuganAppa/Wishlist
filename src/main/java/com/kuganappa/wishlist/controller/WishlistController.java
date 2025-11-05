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

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "registerUser";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                               @RequestParam String confirmPassword,
                               Model model) {

        // Tjek om brugernavn findes
        if (userService.usernameExists(user.getUserName())) {
            model.addAttribute("error", "Brugernavnet findes allerede!");
            return "registerUser";
        }

        // Tjek om email findes
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Emailen findes allerede!");
            return "registerUser";
        }

        // Tjek om password matcher confirmPassword
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Adgangskoderne matcher ikke!");
            return "registerUser";
        }

        // Opret bruger i databasen
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
        model.addAttribute("wishes", wishService.getAllWishes());
        return "showWishes";
    }


    // Specific wish
    @GetMapping("/wishes/{wishId}")
    public String showWish(@PathVariable int wishId, Model model){
        model.addAttribute("wish", wishService.getWish(wishId));
        return "showSpecificWish";
    }


    //add wish to wishlist
    @GetMapping("/wishes/select")
    public String showAllWishesForSelection(@RequestParam int wishlistId, Model model) {
        model.addAttribute("wishlistId", wishlistId);
        model.addAttribute("allWishes", wishService.getAllWishes()); // fx findAll i DB
        return "selectWishes"; // thymeleaf-side
    }

    @GetMapping("/wishlists/addWish")
    public String addWishToWishlist(@RequestParam int wishlistId, @RequestParam int wishId) {
        wishlistService.addWishToWishlist(wishlistId, wishId);
        return "redirect:/wishy/wishlists/" + wishlistId;
    }


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


    @GetMapping("/createWish")
    public String showCreateWishForm(@RequestParam(required = false) Integer wishlistId, Model model) {
        model.addAttribute("wishlistId", wishlistId); // kan være null
        return "createWish";
    }




    @PostMapping("/createWish")
    public String createWish(@RequestParam String wishName,
                             @RequestParam String description,
                             @RequestParam double price,
                             @RequestParam(required = false) String pictureLink,
                             @RequestParam(required = false) String purchaseLink) {

        // Opret og gem ønsket
        Wish wish = new Wish();
        wish.setWishName(wishName);
        wish.setDescription(description);
        wish.setPrice(price);
        wish.setPictureLink(pictureLink);
        wish.setPurchaseLink(purchaseLink);

        wishService.createWish(wish); // gemmer i DB

        // Redirect til alle ønsker
        return "redirect:/wishy/wishes";
    }






}