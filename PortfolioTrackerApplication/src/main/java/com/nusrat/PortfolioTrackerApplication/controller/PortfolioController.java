package com.nusrat.PortfolioTrackerApplication.controller;

import com.nusrat.PortfolioTrackerApplication.entities.Portfolio;
import com.nusrat.PortfolioTrackerApplication.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Portfolio> addPortfolio(
            @PathVariable Long userId,
            @RequestBody Portfolio portfolio
    ) {
        Portfolio createdPortfolio = portfolioService.addPortfolio(userId, portfolio);
        return ResponseEntity.ok(createdPortfolio);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Portfolio>> getPortfolios(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.getPortfoliosByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> getPortfolioById(@PathVariable Long id) {
        Portfolio portfolio = portfolioService.getPortfolioById(id);
        return ResponseEntity.ok(portfolio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
        return ResponseEntity.noContent().build();
    }
}
