package com.miczarne.cia.openfood.service;

import com.miczarne.cia.openfood.model.ProductSearchResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenFoodFactsService {
    private final RestTemplate restTemplate;

    public OpenFoodFactsService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ProductSearchResponse searchProducts(String query) {
        String url = String.format("https://pl.openfoodfacts.org/cgi/search.pl?search_terms=%s&search_simple=1&action=process&json=1", query);
        return restTemplate.getForObject(url, ProductSearchResponse.class);
    }

}
