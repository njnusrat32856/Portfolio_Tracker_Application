import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  private apiUrl = 'http://localhost:8008/api/stocks'; 

  constructor(private http: HttpClient) {}

  // Fetch all stocks in a specific portfolio
  getStocks(portfolioId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/portfolio/${portfolioId}`);
  }

  // Add a new stock to a portfolio
  addStock(stock: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, stock);
  }

  // Delete a stock
  deleteStock(stockId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${stockId}`);
  }
}
