package org.redlotus.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

/*
* The FakeStore API returns getProductById in JSON  format and the 'category' will be a string value.
* But we have 'category' as a class. So we need a DTO to convert the incoming object
*
* */




@Getter @Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;

}
