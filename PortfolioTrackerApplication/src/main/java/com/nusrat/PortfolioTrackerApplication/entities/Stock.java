package com.nusrat.PortfolioTrackerApplication.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String stockName;

    @Column(nullable = false, unique = true)
    private String ticker;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double buyPrice;

    private Double currentPrice; // Updated dynamically from API

    @ManyToOne
    @JoinColumn(name = "porfolio_id", nullable = false)
    private Portfolio portfolio;

//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getStockName() {
//        return stockName;
//    }
//
//    public void setStockName(String stockName) {
//        this.stockName = stockName;
//    }
//
//    public String getTicker() {
//        return ticker;
//    }
//
//    public void setTicker(String ticker) {
//        this.ticker = ticker;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Double getBuyPrice() {
//        return buyPrice;
//    }
//
//    public void setBuyPrice(Double buyPrice) {
//        this.buyPrice = buyPrice;
//    }
//
//    public Double getCurrentPrice() {
//        return currentPrice;
//    }
//
//    public void setCurrentPrice(Double currentPrice) {
//        this.currentPrice = currentPrice;
//    }

    //free api: 4FNS200FIXXE11OD
}
