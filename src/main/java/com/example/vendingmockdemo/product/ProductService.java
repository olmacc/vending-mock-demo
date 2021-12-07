package com.example.vendingmockdemo.product;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductService {

    public Product orderProduct(int productId) {
        return Arrays.stream(Product.values())
                .filter(product -> product.getId() == productId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Product with ID [%s] doesn't exist", productId)));
    }

}
