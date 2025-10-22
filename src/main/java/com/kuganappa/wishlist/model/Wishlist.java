package com.kuganappa.wishlist.model;

import java.util.List;

public class Wishlist {
    private final int wishlistId;
    private String wishlistName;
    private int ownerId;
    private String description;
    private List<Wish> wishes;

    public Wishlist(int wishlistId, String wishlistName, int ownerId, String description, List<Wish> wishes) {
        this.wishlistId = wishlistId;
        this.wishlistName = wishlistName;
        this.ownerId = ownerId;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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