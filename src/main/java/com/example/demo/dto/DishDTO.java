package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DishDTO {
    private String name;

    private double caloriesPerServing;
    private double fat;
    private double protein;
    private double carbohydrates;
}
