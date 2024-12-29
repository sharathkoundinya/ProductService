package org.redlotus.productservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArrayIndexOutOfBoundsExceptionDto {
    private String message;
    private String details;

    public String getMessage() {
        return message;
    }

    public ArrayIndexOutOfBoundsExceptionDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public ArrayIndexOutOfBoundsExceptionDto setDetails(String details) {
        this.details = details;
        return this;
    }
}
