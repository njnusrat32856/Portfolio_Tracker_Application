package com.nusrat.PortfolioTrackerApplication.services;

import com.nusrat.PortfolioTrackerApplication.entities.Portfolio;
import com.nusrat.PortfolioTrackerApplication.entities.User;
import com.nusrat.PortfolioTrackerApplication.repositories.PortfolioRepository;
import com.nusrat.PortfolioTrackerApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    public Portfolio addPortfolio(Long userId, Portfolio portfolio) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        portfolio.setUser(user);
        return portfolioRepository.save(portfolio);
    }

    public List<Portfolio> getPortfoliosByUser(Long userId) {
        return portfolioRepository.findByUserId(userId);
    }

    public Portfolio getPortfolioById(Long portfolioId) {
        return portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new RuntimeException("Portfolio not found with ID: " + portfolioId));
    }


    public void deletePortfolio(Long portfolioId) {
        portfolioRepository.deleteById(portfolioId);
    }
}
