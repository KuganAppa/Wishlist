package com.kuganappa.wishlist.model;

import java.util.List;

public class Wishlist {
    private final int wishlistId;
    private String wishlistName;
    private String ownerName;
    private String description;
    private List<Wish> wishes;

    public Wishlist(int wishlistId, String wishlistName, String ownerName, String description, List<Wish> wishes) {
        this.wishlistId = wishlistId;
        this.wishlistName = wishlistName;
        this.ownerName = ownerName;
        this.description = description;
        this.wishes = wishes;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
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