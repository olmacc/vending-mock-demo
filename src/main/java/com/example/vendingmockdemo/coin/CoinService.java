package com.example.vendingmockdemo.coin;

import com.example.vendingmockdemo.info.InfoPanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinService {

    private final InfoPanelService infoPanelService;
    private int currentCents;
    private List<Coin> coins;

    {
        init();
    }

    private void init() {
        currentCents = 0;
        coins = new ArrayList<>();
    }

    public int insertCoin(Coin coin) {
        coins.add(coin);
        currentCents += coin.getCents();
        infoPanelService.showTotalAmount(currentCents);
        return currentCents;
    }

    public List<Coin> returnCoins() {
        List<Coin> coinsToReturn = new ArrayList<>(coins);
        infoPanelService.showCoinsReturn(coinsToReturn);
        init();
        infoPanelService.showTotalAmount(currentCents);
        return coinsToReturn;
    }

}
