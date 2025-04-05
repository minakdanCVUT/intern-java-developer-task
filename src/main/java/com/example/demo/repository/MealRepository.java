package com.example.demo.repository;

import com.example.demo.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Long, Meal> {
}
