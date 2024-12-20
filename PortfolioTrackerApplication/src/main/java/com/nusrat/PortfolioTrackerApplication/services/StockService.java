package com.nusrat.PortfolioTrackerApplication.services;

import com.nusrat.PortfolioTrackerApplication.entities.Portfolio;
import com.nusrat.PortfolioTrackerApplication.entities.Stock;
import com.nusrat.PortfolioTrackerApplication.repositories.PortfolioRepository;
import com.nusrat.PortfolioTrackerApplication.repositories.StockRepository;
import com.nusrat.PortfolioTrackerApplication.util.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockPriceService stockPriceService;

    @Autowired
    private PortfolioRepository portfolioRepository;

//    public List<Stock> getAllStocks() {
//        return stockRepository.findAll();
//    }

    public List<Stock> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        stocks.forEach(stock -> {
            Double currentPrice = stockPriceService.getStockPrice(stock.getTicker());
            if (currentPrice != null) {
                stock.setCurrentPrice(currentPrice);
            }
        });
        return stocks;
    }

    //    public double calculatePortfolioValue(List<Stock> stocks) {
//        return stocks.stream()
//                .mapToDouble(stock -> stock.getCurrentPrice() * stock.getQuantity())
//                .sum();
//    }
//    public double calculatePortfolioValue(List<Stock> stocks) {
//        double totalValue = 0.0;
//
//        for (Stock stock : stocks) {
//            Double currentPrice = stockPriceService.getStockPrice(stock.getTicker());
//            if (currentPrice != null) {
//                stock.setCurrentPrice(currentPrice);
//                totalValue += currentPrice * stock.getQuantity();
//            }
//        }
//
//        return totalValue;
//    }
    public double calculatePortfolioValue(Long portfolioId) {
        List<Stock> stocks = stockRepository.findByPortfolioId(portfolioId);
        return stocks.stream()
                .mapToDouble(stock -> {
                    double currentPrice = stockPriceService.getStockPrice(stock.getTicker());
                    return currentPrice * stock.getQuantity();
                })
                .sum();
    }

    public Stock addStock(Long portfolioId, Stock stock) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio not found with ID: " + portfolioId));
        stock.setPortfolio(portfolio);
        return stockRepository.save(stock);
    }

    public List<Stock> getStocksByPortfolio(Long portfolioId) {
        return stockRepository.findByPortfolioId(portfolioId);
    }

    public Stock updateStock(Long id, Stock stock) {
        Stock existingStock = stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found with ID: " + id));
        existingStock.setStockName(stock.getStockName());
        existingStock.setTicker(stock.getTicker());
        existingStock.setQuantity(stock.getQuantity());
        existingStock.setBuyPrice(stock.getBuyPrice());
        return stockRepository.save(existingStock);
    }

    public void deleteStock(Long id) {
        if (!stockRepository.existsById(id)) {
            throw new RuntimeException("Stock not found with ID: " + id);
        }
        stockRepository.deleteById(id);
    }

}
