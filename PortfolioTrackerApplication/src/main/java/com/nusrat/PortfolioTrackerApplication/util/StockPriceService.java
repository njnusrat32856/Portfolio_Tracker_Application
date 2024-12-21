package com.nusrat.PortfolioTrackerApplication.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class StockPriceService {

    private final WebClient webClient;

    public StockPriceService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.alphavantage.co").build();
    }

    public BigDecimal getStockPrice(String ticker) {
        String apiKey = "4FNS200FIXXE11OD"; // Replace with your API key
        String url = "/query?function=GLOBAL_QUOTE&symbol=" + ticker + "&apikey=" + apiKey;

        try {
            // Send API request
            String response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Parse and extract stock price
            BigDecimal price = parsePriceFromResponse(response);
            return price;
        } catch (Exception e) {
            // Log error and return null
            e.printStackTrace();
            return null;
        }
//        try {
//            String price = webClient.get()
//                    .uri(url)
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .map(response -> parsePrice(response))
//                    .block();
//            return price != null ? BigDecimal.valueOf(price) : null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    private BigDecimal parsePriceFromResponse(String response) {
        // Extract the current price from the JSON response
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode priceNode = rootNode.path("Global Quote").path("05. price");
            return new BigDecimal(priceNode.asText());
        } catch (Exception e) {
            throw new RuntimeException("Error parsing stock price from API response", e);
        }
    }
}
