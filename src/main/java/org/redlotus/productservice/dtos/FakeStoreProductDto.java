package org.redlotus.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

/*
* The FakeStore API returns getProductById in JSON  format and the 'category' will be a string value.
* But we have 'category' as a class. So we need a DTO to convert the incoming object
*
* */





public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;

    public FakeStoreProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public FakeStoreProductDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public FakeStoreProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public FakeStoreProductDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public FakeStoreProductDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public FakeStoreProductDto setImage(String image) {
        this.image = image;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
