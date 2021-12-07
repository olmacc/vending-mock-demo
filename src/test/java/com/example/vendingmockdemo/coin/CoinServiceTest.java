package com.example.vendingmockdemo.coin;

import com.example.vendingmockdemo.info.InfoPanelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CoinServiceTest {

    @InjectMocks
    private CoinService underTest;

    @Mock
    private InfoPanelService infoPanelService;

    @Test
    public void addingCoinsIncreasesTotalTest() {
        underTest.insertCoin(Coin.TWO_EUR);
        underTest.insertCoin(Coin.TWO_CENTS);
        assertEquals(202, underTest.getTotal());
        InOrder inOrder = Mockito.inOrder(infoPanelService);
        inOrder.verify(infoPanelService).showTotalAmount(200);
        inOrder.verify(infoPanelService).showTotalAmount(202);
    }

    @ParameterizedTest
    @MethodSource("provideCalculationArguments")
    public void calculateCoinsTest(int total, int expected) {
        assertEquals(expected, underTest.calculateReturnCoins(new ArrayList<>(List.of(Coin.values())), total).size());
    }

    private static Stream<Arguments> provideCalculationArguments() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 1),
                Arguments.of(2, 1),
                Arguments.of(3, 2),
                Arguments.of(4, 2),
                Arguments.of(5, 1),
                Arguments.of(6, 2),
                Arguments.of(7, 2),
                Arguments.of(8, 3),
                Arguments.of(9, 3),
                Arguments.of(10, 1),
                Arguments.of(388, 8)
        );
    }
}
