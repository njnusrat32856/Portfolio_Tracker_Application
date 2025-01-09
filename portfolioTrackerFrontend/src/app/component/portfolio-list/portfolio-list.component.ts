import { Component, OnInit } from '@angular/core';
import { PortfolioService } from '../../services/portfolio.service';

@Component({
  selector: 'app-portfolio-list',
  templateUrl: './portfolio-list.component.html',
  styleUrl: './portfolio-list.component.css'
})
export class PortfolioListComponent implements OnInit{

  portfolios: any[] = [];

  constructor(private portfolioService: PortfolioService) {}

  ngOnInit(): void {
    this.fetchPortfolios();
  }

  fetchPortfolios() {
    const userId = 1; // Example user ID
    this.portfolioService.getPortfolios(userId).subscribe((data) => {
      this.portfolios = data;
    });
  }

}
