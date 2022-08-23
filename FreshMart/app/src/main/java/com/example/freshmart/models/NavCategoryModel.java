package com.example.freshmart.models;

public class NavCategoryModel {
    
    String name;
    String discount;
    String img_url;
    String description;
    String type;

    public NavCategoryModel() {
        
    }

    public NavCategoryModel(String name, String discount, String img_url, String description,String type) {
        this.name = name;
        this.discount = discount;
        this.img_url = img_url;
        this.description = description;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
