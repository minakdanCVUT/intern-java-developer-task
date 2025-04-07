package com.example.demo.controller;

import com.example.demo.dto.MealDTO;
import com.example.demo.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/meals")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createMeal(@RequestBody MealDTO dto, @RequestParam String name){
        mealService.createMeal(dto, name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
