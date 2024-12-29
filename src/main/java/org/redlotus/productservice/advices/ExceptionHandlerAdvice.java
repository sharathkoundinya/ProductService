package org.redlotus.productservice.advices;

import org.redlotus.productservice.dtos.ArithmeticExceptionDto;
import org.redlotus.productservice.dtos.ArrayIndexOutOfBoundsExceptionDto;
import org.redlotus.productservice.dtos.GenericExceptionDto;
import org.redlotus.productservice.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
*  NOTE : This ControllerAdvice will be used by all controllers
*  HOW : What if we want to use a specific ControllerAdvice for ProductController?
*           Instead of using another class, create a method inside the ProductController and annotate with
*           @ExceptionHandler.
*
*
*
* */


@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException() {
        ArithmeticExceptionDto dto = new ArithmeticExceptionDto();
        dto.setMessage("This is an Arithmetic Exception");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ArrayIndexOutOfBoundsExceptionDto> handleAIOBException() {
        ArrayIndexOutOfBoundsExceptionDto dto = new ArrayIndexOutOfBoundsExceptionDto();
        dto.setMessage("This is an Array Index Out Of Bounds Exception");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<GenericExceptionDto> handleInvalidProductIdException(InvalidProductIdException exception) {
        GenericExceptionDto dto = new GenericExceptionDto();
        dto.setMessage("Invalid Product Id- " + exception.getProductId() + " passed. Please try with a valid ID");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }



}
