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
        this.description = description;
        this.owner = owner;
        this.wishes = wishes;
    }

    public Wishlist(String wishlistName, User owner, String description, List<Wish> wishes) {
        this.wishlistName = wishlistName;
        this.description = description;
        this.owner = owner;
        this.wishes = wishes;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Wishlist() {
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

    public void setWishes(List<Wish> wishes) {
        this.wishes = wishes;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "wishlistId=" + wishlistId +
                ", wishlistName='" + wishlistName + '\'' +
                ", owner=" + (owner != null ? owner.getUserName() : "null") +
                ", description='" + description + '\'' +
                ", wishes=" + (wishes != null ? wishes.size() : 0) +
                '}';
    }

}