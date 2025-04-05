package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NutritionStats {
    private double calories;
    private double protein;
    private double fat;
    private double carbs;

    public void addStats(NutritionStats stats){
        this.calories += stats.calories;
        this.fat += stats.fat;
        this.carbs += stats.carbs;
        this.protein += stats.protein;
    }
}
