import { Component, Input, OnInit } from '@angular/core';
import { StockService } from '../../services/stock.service';
import { StockPriceService } from '../../services/stock-price.service';

@Component({
  selector: 'app-stock-management',
  templateUrl: './stock-management.component.html',
  styleUrl: './stock-management.component.css'
})
export class StockManagementComponent implements OnInit{


  stocks: any[] = [
    { ticker: 'AAPL', currentPrice: 0 },
    { ticker: 'TSLA', currentPrice: 0 },
  ];

  constructor(private stockPriceService: StockPriceService) {}

  ngOnInit(): void {
    this.stocks.forEach((stock) => {
      this.stockPriceService.getPrice(stock.ticker).subscribe((price) => {
        stock.currentPrice = price;
      });
    });
  }

  // @Input() portfolioId!: number;
  // stock: any = {
  //   stockName: '',
  //   ticker: '',
  //   quantity: 1,
  //   buyPrice: 0,
  // };

  // constructor(private stockService: StockService) {}

  // addStock() {
  //   const newStock = { ...this.stock, portfolioId: this.portfolioId };
  //   this.stockService.addStock(newStock).subscribe(() => {
  //     console.log('Stock added successfully');
  //   });
  // }

}
