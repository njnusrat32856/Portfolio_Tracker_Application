package com.nusrat.PortfolioTrackerApplication.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StockPriceService {

    private final WebClient webClient;

    public StockPriceService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.alphavantage.co").build();
    }

    public Double getStockPrice(String ticker) {
        String apiKey = "4FNS200FIXXE11OD"; // Replace with your API key
        String url = "/query?function=GLOBAL_QUOTE&symbol=" + ticker + "&apikey=" + apiKey;

        try {
            String price = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(response -> parsePrice(response))
                    .block();
            return price != null ? Double.parseDouble(price) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String parsePrice(String response) {
        // Parse JSON response to extract the stock price
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("Global Quote").path("05. price").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
