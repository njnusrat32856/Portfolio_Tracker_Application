import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PortfolioService {

  private apiUrl = 'http://localhost:8008/api/portfolios'; 

  constructor(private http: HttpClient) {}

  // Fetch all portfolios for a specific user
  getPortfolios(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${userId}`);
  }

  // Create a new portfolio
  createPortfolio(portfolio: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, portfolio);
  }
}
