package com.example.demo.controller;

import com.example.demo.dto.DishDTO;
import com.example.demo.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDish(@RequestBody DishDTO dto){
        dishService.createDish(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
