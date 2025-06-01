package com.miczarne.cia.openfood.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutriments {
    private double energy_100g;
    private double fat_100g;
    private double sugars_100g;
}
