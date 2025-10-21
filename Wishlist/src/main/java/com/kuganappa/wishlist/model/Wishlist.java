package com.kuganappa.wishlist.model;

import java.util.List;

public class Wishlist {
    private final int wishListId;
    private String wishlistName;
    private User ownerName;
    private String description;
    private List<Wish> wishes;

    public Wishlist(int wishListId, String wishlistName, User ownerName, String description, List<Wish> wishes) {
        this.wishListId = wishListId;
        this.wishlistName = wishlistName;
        this.ownerName = ownerName;
        this.description = description;
        this.wishes = wishes;
    }

    public int getWishListId() {
        return wishListId;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public User getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(User ownerName) {
        this.ownerName = ownerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }
}