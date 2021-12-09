package com.example.vendingmockdemo.coin;

import com.example.vendingmockdemo.info.InfoPanelService;
import com.example.vendingmockdemo.product.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CoinServiceTest {

    @InjectMocks
    private CoinService underTest;

    @Spy
    private InfoPanelService infoPanelService;

    @Test
    public void given_zeroTotal_when_insertCoins_then_calculateTotal() {
        underTest.insertCoin(Coin.TWO_EUR);
        underTest.insertCoin(Coin.TWO_CENTS);
        assertEquals(202, underTest.getTotal());
    }

    @Test
    public void given_zeroTotal_when_insertCoins_then_showTotal() {
        underTest.insertCoin(Coin.TWENTY_CENTS);
        underTest.insertCoin(Coin.TEN_CENTS);
        underTest.insertCoin(Coin.FIVE_CENTS);
        underTest.insertCoin(Coin.FIVE_CENTS);
        underTest.insertCoin(Coin.TWO_CENTS);
        InOrder inOrder = Mockito.inOrder(infoPanelService);
        inOrder.verify(infoPanelService).showTotalAmount(20);
        inOrder.verify(infoPanelService).showTotalAmount(30);
        inOrder.verify(infoPanelService).showTotalAmount(35);
        inOrder.verify(infoPanelService).showTotalAmount(40);
        inOrder.verify(infoPanelService).showTotalAmount(42);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/coin-return-calculation.csv")
    public void when_calculateReturnCoins_then_correctCoinCount(int total, int expected) {
        assertEquals(
                expected,
                underTest
                        .calculateReturnCoins(new ArrayList<>(List.of(Coin.values())), total)
                        .size());
    }

    @ParameterizedTest
    @EnumSource(Product.class)
    public void when_payProduct_then_subtractFromTotal(Product product) {
        underTest.insertCoin(Coin.TWO_EUR);
        int cost = product.getCents();
        underTest.pay(cost);
        assertEquals(200 - cost, underTest.getTotal());
    }

}
