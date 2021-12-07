package com.example.vendingmockdemo.coin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Coin {
    ONE_CENT(1),
    TWO_CENTS(2),
    FIVE_CENTS(5),
    TEN_CENTS(10),
    TWENTY_CENTS(20),
    FIFTY_CENTS(50),
    ONE_EUR(100),
    TWO_EUR(200);

    private final int cents;

}
