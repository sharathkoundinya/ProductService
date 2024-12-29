package org.redlotus.productservice.dtos;

import lombok.Getter;
import lombok.Setter;



public class GenericExceptionDto {

    private String message;


    public String getMessage() {
        return message;
    }

    public GenericExceptionDto setMessage(String message) {
        this.message = message;
        return this;
    }

}
