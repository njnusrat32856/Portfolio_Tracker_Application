import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StockPriceService {

  private apiUrl = 'http://localhost:8008/api/prices'; 

  constructor(private http: HttpClient) {}

  // Fetch the real-time price for a specific stock
  getPrice(ticker: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${ticker}`);
  }
}
