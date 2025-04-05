package com.example.demo.controller;

import com.example.demo.service.MealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meals")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }
}
