package com.nusrat.PortfolioTrackerApplication.repositories;

import com.nusrat.PortfolioTrackerApplication.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {


}
