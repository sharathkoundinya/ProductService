package org.redlotus.productservice.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class ProductRequestDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private double price;

    public Long getId() {
        return id;
    }

    public ProductRequestDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ProductRequestDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductRequestDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ProductRequestDto setCategory(String category) {
        this.category = category;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductRequestDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ProductRequestDto setImage(String image) {
        this.image = image;
        return this;
    }

    private String image;

}
