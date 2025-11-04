package com.kuganappa.wishlist.model;

public class Wish {
    private int wishId;
    private String wishName;
    private String description;
    private double price;
    private String pictureLink;
    private String purchaseLink;

    public Wish(String wishName, String description, double price, String pictureLink, String purchaseLink) {
        this.wishName = wishName;
        this.description = description;
        this.price = price;
        this.pictureLink = pictureLink;
        this.purchaseLink = purchaseLink;
    }

    public Wish() {

    }

    public int getWishId() {
        return wishId;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
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

    public String getPurchaseLink() {
        return purchaseLink;
    }

    public void setPurchaseLink(String purchaseLink) {
        this.purchaseLink = purchaseLink;
    }
}
