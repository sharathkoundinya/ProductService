package org.redlotus.productservice.services;

import org.redlotus.productservice.dtos.FakeStoreProductDto;
import org.redlotus.productservice.models.Category;
import org.redlotus.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
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
    public Product getProductById(Long id) {
        // Call the FakeStore API to get the product with given Id
       FakeStoreProductDto fakeStoreProductDto =
               restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class, id);

        //Convert fakeStoreProductDto to product object

        if(fakeStoreProductDto == null) {return null;}

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
        * */
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
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
