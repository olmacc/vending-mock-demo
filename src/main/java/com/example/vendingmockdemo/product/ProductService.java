package com.example.vendingmockdemo.product;

import com.example.vendingmockdemo.info.InfoPanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final InfoPanelService infoPanelService;
    private Map<Product, Integer> stock;
    {
        init();
    }

    /**
     * The machine can only be fully refilled and afterwards restarted.
     */
    private void init() {
        stock = new HashMap<>();
        stock.put(Product.WATER, 5);
        stock.put(Product.LEMONADE, 5);
        stock.put(Product.JUICE, 5);
        stock.put(Product.BEER, 5);
    }

    public Product getProduct(int id) {
        infoPanelService.showProductRequested(id);
        return findProduct(id);
    }

    public Product deliverProduct(Product product) {
        int amount = stock.get(product);
        if (amount == 0) {
            throw new UnsupportedOperationException(String.format("Product [%s] is out of stock", product));
        }
        stock.put(product, stock.get(product) - 1);
        infoPanelService.showProductDelivered(product);
        return product;
    }

    private Product findProduct(int productId) {
        return Arrays.stream(Product.values())
                .filter(product -> product.getId() == productId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Product with ID [%s] doesn't exist", productId)));
    }

}
