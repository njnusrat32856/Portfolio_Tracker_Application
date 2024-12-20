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

    @PostMapping("/portfolio/{portfolioId}")
    public ResponseEntity<Stock> addStock(
            @PathVariable Long portfolioId,
            @RequestBody Stock stock
    ) {
        Stock createdStock = stockService.addStock(portfolioId, stock);
        return ResponseEntity.ok(createdStock);
    }

    @GetMapping("/portfolio/{portfolioId}")
    public ResponseEntity<List<Stock>> getStocksByPortfolio(@PathVariable Long portfolioId) {
        List<Stock> stocks = stockService.getStocksByPortfolio(portfolioId);
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/portfolio/{portfolioId}/value")
    public ResponseEntity<Double> getPortfolioValue(@PathVariable Long portfolioId) {
        double portfolioValue = stockService.calculatePortfolioValue(portfolioId);
        return ResponseEntity.ok(portfolioValue);
    }

//    @GetMapping("/view")
//    public ResponseEntity<Map<String, Object>> getAllStocks() {
//        List<Stock> stocks = stockService.getAllStocks();
//        double totalValue = stockService.calculatePortfolioValue(stocks);
//        Map<String, Object> response = Map.of(
//                "stocks", stocks,
//                "totalValue", totalValue
//        );
//        return ResponseEntity.ok(response);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Stock> updateStock(
            @PathVariable Long id,
            @RequestBody Stock stock) {
        Stock updatedStock = stockService.updateStock(id, stock);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/deletes/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

}
