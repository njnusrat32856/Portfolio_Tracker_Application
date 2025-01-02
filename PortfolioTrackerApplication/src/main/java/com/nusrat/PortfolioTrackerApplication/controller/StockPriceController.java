package com.nusrat.PortfolioTrackerApplication.controller;

import com.nusrat.PortfolioTrackerApplication.util.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/prices")
public class StockPriceController {

    @Autowired
    private StockPriceService stockPriceService;

    @GetMapping("/{ticker}")
    public ResponseEntity<BigDecimal> getPrice(@PathVariable String ticker) {
        BigDecimal price = stockPriceService.getRealTimePrice(ticker);
        return ResponseEntity.ok(price);
    }

}
