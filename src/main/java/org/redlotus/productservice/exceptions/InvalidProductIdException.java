package org.redlotus.productservice.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
public class InvalidProductIdException extends Exception {


    private Long productId;

    //constructor
    public InvalidProductIdException(Long productId, String message) {
        super(message);
        this.productId = productId;
    }
}
