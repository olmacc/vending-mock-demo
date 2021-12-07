package com.example.vendingmockdemo.info;

import com.example.vendingmockdemo.coin.Coin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoPanelService {

    public void showTotalAmount(int cents) {
        System.out.println("Total: " + cents);
    }

    public void showCoinsReturn(List<Coin> coins) {
        System.out.println("Returning Coins..." + coins);
    }
}
