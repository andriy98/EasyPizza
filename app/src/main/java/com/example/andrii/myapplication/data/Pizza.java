package com.example.andrii.myapplication.data;

import com.google.firebase.database.PropertyName;

public class Pizza {
    @PropertyName("Опис:")
    private String description;
    @PropertyName("Розміри:")
    private String sizes;
    @PropertyName("Фото")
    private String photo;
    @PropertyName("Ціна")
    private String price;

    public String getDescription() {
        return description;
    }

    public String getSizes() {
        return sizes;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pizza{");
        sb.append("description='").append(description).append('\'');
        sb.append(", sizes='").append(sizes).append('\'');
        sb.append(", photo='").append(photo).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
