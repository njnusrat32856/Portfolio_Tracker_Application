package com.nusrat.PortfolioTrackerApplication.controller;

import com.nusrat.PortfolioTrackerApplication.entities.Stock;
import com.nusrat.PortfolioTrackerApplication.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        Stock savedStock = stockService.addStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStock);
    }

    @GetMapping("/view")
    public ResponseEntity<Map<String, Object>> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        double totalValue = stockService.calculatePortfolioValue(stocks);
        Map<String, Object> response = Map.of(
                "stocks", stocks,
                "totalValue", totalValue
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        Stock updatedStock = stockService.updateStock(id, stock);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/deletes/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

}
