import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  @Input() stocks: any[] = [];
  totalValue: number = 0;
  topStock: any = null;

  ngOnInit(): void {
    this.calculateMetrics();
  }

  calculateMetrics() {
    this.totalValue = this.stocks.reduce((sum, stock) => sum + stock.buyPrice * stock.quantity, 0);
    this.topStock = this.stocks.reduce((prev, current) => (prev.buyPrice > current.buyPrice ? prev : current), {});
  }

}
