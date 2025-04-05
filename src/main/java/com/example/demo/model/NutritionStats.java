package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nutrition_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NutritionStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double calories;
    private double protein;
    private double fat;
    private double carbs;

    @OneToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @OneToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @OneToOne
    @JoinColumn(name = "daily_meals_id")
    private DailyMeals dailyMeals;
}
