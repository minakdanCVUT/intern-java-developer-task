package com.example.demo.repository;

import com.example.demo.model.Dish;
import com.example.demo.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("select dm.dish from DishMeal dm where dm.meal.id = :mealId")
    List<Dish> getDishesByMeal(@Param("mealId") Long mealId);
}
