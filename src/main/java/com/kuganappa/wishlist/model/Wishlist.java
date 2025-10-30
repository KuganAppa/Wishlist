package com.kuganappa.wishlist.model;

import java.util.List;
import java.util.SequencedCollection;

public class Wishlist {
    private int wishlistId;
    private String wishlistName;
    private User owner;
    private String description;
    private List<Wish> wishes;

    public Wishlist(int wishlistId, String wishlistName, User owner, String description, List<Wish> wishes) {
        this.wishlistId = wishlistId;
        this.wishlistName = wishlistName;
        this.owner = owner;
        this.description = description;
        this.wishes = wishes;
    }



    public Wishlist() {
    }


    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int i) {
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

}