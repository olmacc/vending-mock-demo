package com.example.vendingmockdemo.coin;

import com.example.vendingmockdemo.info.InfoPanelService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinService {

    private final InfoPanelService infoPanelService;

    @Getter
    private int total;

    private List<Coin> coins;

    {
        init();
    }

    private void init() {
        total = 0;
        coins = new ArrayList<>();
    }

    public int insertCoin(Coin coin) {
        coins.add(coin);
        total += coin.getCents();
        infoPanelService.showTotalAmount(total);
        return coin.getCents();
    }

    public List<Coin> returnCoins() {
        List<Coin> coinsToReturn = new ArrayList<>(coins);
        infoPanelService.showCoinsReturn(coinsToReturn);
        init();
        infoPanelService.showTotalAmount(total);
        return coinsToReturn;
    }

    public void pay(int cents) {
        checkTotalSufficient(cents);
        total -= cents;
        coins = new ArrayList<>(); // can't relate coins after paying
        infoPanelService.showTotalAmount(total);
    }

    public void checkTotalSufficient(int cents) {
        if (cents > total) {
            throw new UnsupportedOperationException(String.format("Please insert %s cents more", cents - total));
        }
    }

}
