package com.miczarne.cia.openfood.controller;

import com.miczarne.cia.openfood.model.ProductSearchResponse;
import com.miczarne.cia.openfood.service.OpenFoodFactsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductSearchController {

    private final OpenFoodFactsService foodFactsService;

    public ProductSearchController(OpenFoodFactsService foodFactsService) {
        this.foodFactsService = foodFactsService;
    }

    @GetMapping("/search")
    public ProductSearchResponse search(@RequestParam String query) {
        return foodFactsService.searchProducts(query);
    }
}
