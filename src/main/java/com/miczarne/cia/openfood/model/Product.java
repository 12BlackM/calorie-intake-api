package com.miczarne.cia.openfood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String product_name;
    private String generic_name;
    private String brands;
    private String code; // barcode
    private String image_url;
    private Nutriments nutriments;
}
