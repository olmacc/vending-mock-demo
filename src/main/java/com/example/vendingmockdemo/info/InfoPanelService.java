package com.example.vendingmockdemo.info;

import com.example.vendingmockdemo.coin.Coin;
import com.example.vendingmockdemo.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoPanelService {

    public void showTotalAmount(int cents) {
        System.out.println("Total: " + cents);
    }

    public void showCoinsReturn(List<Coin> coins) {
        System.out.println("Returning Coins: " + coins);
    }

    public void showProductRequested(int productId) {
        System.out.println("Requesting Product: " + productId);
    }

    public void showProductDelivered(Product product) {
        System.out.println("Product delivered: " + product);
    }
}
