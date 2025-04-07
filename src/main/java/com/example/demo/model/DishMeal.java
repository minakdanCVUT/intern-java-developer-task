package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dish_meal")
@Data
@NoArgsConstructor
public class DishMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="dish_id", nullable = false)
    private Dish dish;

    @ManyToOne
    @JoinColumn(name="meal_id", nullable = false)
    private Meal meal;

    public DishMeal(Dish dish, Meal meal) {
        this.dish = dish;
        this.meal = meal;
    }
}
