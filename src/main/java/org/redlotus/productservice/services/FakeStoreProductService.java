package org.redlotus.productservice.services;

import org.redlotus.productservice.dtos.FakeStoreProductDto;
import org.redlotus.productservice.dtos.ProductRequestDto;
import org.redlotus.productservice.exceptions.InvalidProductIdException;
import org.redlotus.productservice.models.Category;
import org.redlotus.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@Primary
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }


    private Product convertFakeProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImage(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }


    private List<Product> convertFakeProductDtoListToProductList(List<FakeStoreProductDto> fakeStoreProductDtoList) {
        List<Product> productList = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoList) {
            productList.add(convertFakeProductDtoToProduct(fakeStoreProductDto));
        }

        return productList;
    }



    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        // Call the FakeStore API to get the product with given Id
       FakeStoreProductDto fakeStoreProductDto =
               restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class, id);

        //Convert fakeStoreProductDto to product object

        if(fakeStoreProductDto == null) {
            throw new InvalidProductIdException(id,  id + " is not a valid ID");
        }

        return convertFakeProductDtoToProduct(fakeStoreProductDto);

    }

    @Override
    public List<Product> getAllProducts() {
        // Call the FakeStore API to get all the products
        /*
        *  1. The response type of https://fakestoreapi.com/products/ is an Array
        *  2. The getForObject method uses the provided response type (FakeStoreProductDto[].class) to determine how to
        *     deserialize the JSON returned by the API.
        *  3. Since the API response is a JSON array, Jackson (the default deserialization library in Spring)
        *     can map it directly to an array of objects.
        *  4. Finally, the Array is converted into ArrayList using Arrays.asList() method
        *
        * Q:Why should the responseType be FakeStoreProductDto[].class and not List<FakeStoreProductDto>.class directly?
        * A: Because the response for the API is received at runtime and as we recall, the runtime has no concept of
        *    datatypes. Everything will be Objects (Type Erasure). So Java cannot convert the FakeStoreProductDto into
        *    a list. Hence, we use the Array.
        *    P.S : If we use our own custom implementation of a List without using Generics and Objects,
        *          then we can use that List at runtime as well.
        *  */
        List<FakeStoreProductDto> fakeStoreProductDtoList =
                Arrays.asList(restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class));

        //Convert fakeStoreProductDto to product object
        return convertFakeProductDtoListToProductList(fakeStoreProductDtoList);

    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, ProductRequestDto productRequestDto) {
        /*
        * > PUT Method
        * > Replace the product with given id with the input product and return the product
        *
        * > Since RestTemplate classes did not have any put method with a return type, write a custom implementation
        *
        * > The input here is Product object (from interface). But we need FakeStoreProductDto as Category is a class in
        *   our implementation. So we need another converter which will convert Product to FakeStoreProductDto
        *
        * */
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productRequestDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeProductDtoToProduct(fakeStoreProductDto);

    }

    @Override
    public void deleteProduct(Long id) {


    }
}
