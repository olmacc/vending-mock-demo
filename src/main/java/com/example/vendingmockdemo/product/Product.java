package com.example.vendingmockdemo.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Product {
    WATER(1, 49),
    LEMONADE(2, 75),
    JUICE(3, 120),
    BEER(4, 180);

    private final int id;
    private final int cents;

}
