package com.example.vendingmockdemo.control;

import com.example.vendingmockdemo.coin.Coin;
import com.example.vendingmockdemo.coin.CoinService;
import com.example.vendingmockdemo.info.InfoPanelService;
import com.example.vendingmockdemo.product.Product;
import com.example.vendingmockdemo.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hmi")
public class VendingMachineController {

    private final CoinService coinService;
    private final ProductService productService;

    public VendingMachineController(InfoPanelService infoPanelService) {
        this.coinService = new CoinService(infoPanelService);
        this.productService = new ProductService(infoPanelService);
    }

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
        return ResponseEntity.ok(coinService.getTotal());
    }

    /**
     * Order a {@link Product} by ID.
     * Checks, whether the inserted amount is sufficient for the ordered {@link Product}ID.
     *
     * @param id the ProductID
     * @return the ordered Product
     */
    @GetMapping("product/{id}")
    public ResponseEntity<Product> orderProduct(@PathVariable int id) {
        Product product = productService.getProduct(id);
        coinService.checkTotalSufficient(product.getCents());
        ResponseEntity.ok(productService.deliverProduct(product));
        coinService.pay(product.getCents());
        return ResponseEntity.ok(product);
    }
}
