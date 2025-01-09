import { Component, Input, OnInit } from '@angular/core';
import { StockService } from '../../services/stock.service';

@Component({
  selector: 'app-portfolio-details',
  templateUrl: './portfolio-details.component.html',
  styleUrl: './portfolio-details.component.css'
})
export class PortfolioDetailsComponent implements OnInit{

  @Input() portfolioId!: number;
  stocks: any[] = [];

  constructor(private stockService: StockService) {}

  ngOnInit(): void {
    if (this.portfolioId) {
      this.fetchStocks();
    }
  }

  fetchStocks() {
    this.stockService.getStocks(this.portfolioId).subscribe((data) => {
      this.stocks = data;
    });
  }

  deleteStock(stockId: number) {
    this.stockService.deleteStock(stockId).subscribe(() => {
      this.fetchStocks(); // Refresh the list after deletion
    });
  }

}
