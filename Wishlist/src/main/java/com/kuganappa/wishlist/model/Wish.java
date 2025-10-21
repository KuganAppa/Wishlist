package com.kuganappa.wishlist.model;

public class Wish {
    private final int wishId;
    private String wishName;
    private String description;
    private double price;
    private String pictureLink;
    private String link;

    public Wish(int wishId, String wishName, String description, double price, String pictureLink, String link) {
        this.wishId = wishId;
        this.wishName = wishName;
        this.description = description;
        this.price = price;
        this.pictureLink = pictureLink;
        this.link = link;
    }

    public int getWishId() {
        return wishId;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
