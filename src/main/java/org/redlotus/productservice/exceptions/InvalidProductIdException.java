package org.redlotus.productservice.exceptions;


import lombok.Getter;
import lombok.Setter;

public class InvalidProductIdException extends Exception {


    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public InvalidProductIdException(Long productId, String message) {
        super(message);
        this.productId = productId;
    }
}
