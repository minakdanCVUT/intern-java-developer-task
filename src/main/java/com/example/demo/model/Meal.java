package com.example.demo.model;

import com.example.demo.enums.MealType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="meal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MealType type;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "daily_meals_id")
    private DailyMeals dailyMeals;

    @Embedded
    private NutritionStats nutritionStats;
}
