import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PortfolioListComponent } from './component/portfolio-list/portfolio-list.component';
import { PortfolioDetailsComponent } from './component/portfolio-details/portfolio-details.component';
import { StockManagementComponent } from './component/stock-management/stock-management.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    PortfolioListComponent,
    PortfolioDetailsComponent,
    StockManagementComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
