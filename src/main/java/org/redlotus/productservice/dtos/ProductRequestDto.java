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
    private String image;

}
