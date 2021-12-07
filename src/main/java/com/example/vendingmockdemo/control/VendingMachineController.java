package com.example.vendingmockdemo.control;

import com.example.vendingmockdemo.coin.Coin;
import com.example.vendingmockdemo.coin.CoinService;
import com.example.vendingmockdemo.product.Product;
import com.example.vendingmockdemo.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hmi")
@RequiredArgsConstructor
public class VendingMachineController {

    private final CoinService coinService;
    private final ProductService productService;

    @PostMapping("coin")
    public ResponseEntity<Integer> insertCoin(@RequestBody Coin coin) {
        return ResponseEntity.ok(coinService.insertCoin(coin));
    }

    @PutMapping("coin")
    public ResponseEntity<List<Coin>> returnCoins() {
        return ResponseEntity.ok(coinService.returnCoins());
    }

    @GetMapping("coin")
    public ResponseEntity<Integer> getTotalAmount() {
        return ResponseEntity.ok(coinService.getCurrentCents());
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> orderProduct(@PathVariable int id) {
        return ResponseEntity.ok(productService.orderProduct(id));
    }
}
