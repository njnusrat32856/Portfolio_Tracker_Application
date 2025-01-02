package com.nusrat.PortfolioTrackerApplication.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class StockPriceService {

    private final String API_KEY = "4FNS200FIXXE11OD";
    private final RestTemplate restTemplate;

    @Autowired
    public StockPriceService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public BigDecimal getRealTimePrice(String ticker) {
        String url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + ticker + "&apikey=" + API_KEY;

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> globalQuote = (Map<String, Object>) response.getBody().get("Global Quote");

        if (globalQuote != null) {
            String price = (String) globalQuote.get("05. price");
            return new BigDecimal(price);
        }

        throw new RuntimeException("Failed to fetch stock price for ticker: " + ticker);
    }

//    private final WebClient webClient;
//
//    public StockPriceService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.baseUrl("https://www.alphavantage.co").build();
//    }
//
//    public BigDecimal getStockPrice(String ticker) {
//        String apiKey = "4FNS200FIXXE11OD"; // Replace with your API key
//        String url = "/query?function=GLOBAL_QUOTE&symbol=" + ticker + "&apikey=" + apiKey;
//
//        try {
//            // Send API request
//            String response = webClient.get()
//                    .uri(url)
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block();
//
//            // Parse and extract stock price
//            BigDecimal price = parsePriceFromResponse(response);
//            return price;
//        } catch (Exception e) {
//            // Log error and return null
//            e.printStackTrace();
//            return null;
//        }
////        try {
////            String price = webClient.get()
////                    .uri(url)
////                    .retrieve()
////                    .bodyToMono(String.class)
////                    .map(response -> parsePrice(response))
////                    .block();
////            return price != null ? BigDecimal.valueOf(price) : null;
////        } catch (Exception e) {
////            e.printStackTrace();
////            return null;
////        }
//    }
//
//    private BigDecimal parsePriceFromResponse(String response) {
//        // Extract the current price from the JSON response
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode rootNode = mapper.readTree(response);
//            JsonNode priceNode = rootNode.path("Global Quote").path("05. price");
//            return new BigDecimal(priceNode.asText());
//        } catch (Exception e) {
//            throw new RuntimeException("Error parsing stock price from API response", e);
//        }
//    }
}
