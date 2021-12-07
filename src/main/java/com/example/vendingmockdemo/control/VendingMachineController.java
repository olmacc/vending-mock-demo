package com.example.vendingmockdemo.control;

import com.example.vendingmockdemo.coin.Coin;
import com.example.vendingmockdemo.coin.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("facade")
@RequiredArgsConstructor
public class VendingMachineController {

    private final CoinService coinService;

    @PostMapping("coin")
    public ResponseEntity<Integer> insertCoin(@RequestBody Coin coin) {
        return ResponseEntity.ok(coinService.insertCoin(coin));
    }

    @PutMapping("coin")
    public ResponseEntity<List<Coin>> returnCoins() {
        return ResponseEntity.ok(coinService.returnCoins());
    }

}
