package org.redlotus.productservice.controllers;

import org.redlotus.productservice.commons.AuthenticationCommons;
import org.redlotus.productservice.dtos.ProductRequestDto;
import org.redlotus.productservice.dtos.Role;
import org.redlotus.productservice.dtos.UserDto;
import org.redlotus.productservice.exceptions.InvalidProductIdException;
import org.redlotus.productservice.exceptions.ProductControllerSpecificException;
import org.redlotus.productservice.models.Product;
import org.redlotus.productservice.services.FakeStoreProductService;
import org.redlotus.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final AuthenticationCommons authenticationCommons;

    ProductController(@Qualifier("selfProductService") ProductService productService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }


    // localhost:2024/products/124
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {

        Product product = null;

/*
        try{
            product = productService.getProductById(id);
           }
        catch (RuntimeException e) {
            System.out.println("Something went wrong");
        }
       NOTE: When you need to send extra objects along with response, use ResponseEntity
         Here, the ResponseEntity sends both product and the statusCode
        return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);

      > Look how bulky the code got for handling just one exception. Hence we moved all this to ExceptionHandlerAdvice
      > It will get executed before the response goes to the client so all the exception handling can be done there
      > It's like the try method is here and the catch blocks are all in the  ExceptionHandlerAdvice
*/
        product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);


    }

    // localhost:2024/products
    @GetMapping("/all/{token}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String token) {

        //validate the token using UserService
        UserDto userDto = authenticationCommons.validateToken(token);

        if(userDto == null) {
            //token is Invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }


        // NOTE: We are verifying that only Admin role can have access to this API
//        boolean isAdmin = false;
//        for(Role role : userDto.getRoles()) {
//            if(role.equals("ADMIN")) {
//                isAdmin = true;
//                break;
//            }
//        }
//
//        if(!isAdmin) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        List<Product> products =  productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Create Product
    @PostMapping("/save/")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Partial Update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Complete replacement
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto productRequestDto) {

        return productService.replaceProduct(id, productRequestDto);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }


    // Use the ExceptionHandlerAdvice class's comments for reference
    @ExceptionHandler(ProductControllerSpecificException.class)
    public ResponseEntity<Void> handleProductControllerSpecificException() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
