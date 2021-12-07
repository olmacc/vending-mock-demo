package com.example.vendingmockdemo.coin;

import com.example.vendingmockdemo.info.InfoPanelService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        coins = calculateReturnCoins(sortCoins(Arrays.asList(Coin.values())), total);
        infoPanelService.showTotalAmount(total);
    }

    public List<Coin> calculateReturnCoins(List<Coin> coins, int amount) {
        List<Coin> exchangeCoins = new ArrayList<>();
        if (amount == 0) {
            return exchangeCoins;
        }
        while (coins.size() > 0) {
            Coin currentCoin = coins.get(coins.size() - 1);
            if (currentCoin.getCents() <= amount) {
                amount -= currentCoin.getCents();
                exchangeCoins.add(currentCoin);
            } else {
                coins.remove(currentCoin);
                exchangeCoins.addAll(calculateReturnCoins(coins, amount));
            }
        }
        return exchangeCoins;
    }

    private static List<Coin> sortCoins(List<Coin> coins) {
        return Arrays.stream(Coin.values())
                .sorted(Comparator.comparingInt(Coin::getCents))
                .collect(Collectors.toList());
    }

    public void checkTotalSufficient(int cents) {
        if (cents > total) {
            throw new UnsupportedOperationException(String.format("Please insert %s cents more", cents - total));
        }
    }

}
