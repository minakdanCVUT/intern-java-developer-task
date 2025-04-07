package com.example.demo.service;

import com.example.demo.dto.DishDTO;
import com.example.demo.model.Dish;
import com.example.demo.model.NutritionStats;
import com.example.demo.repository.DishRepository;
import org.springframework.stereotype.Service;


@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void createDish(DishDTO dto) {
        checkIfExist(dto.getName());
        // проверка введенных данных
        validateData(dto);
        NutritionStats stats = new NutritionStats(dto.getCaloriesPerServing(), dto.getProtein(),
                dto.getFat(), dto.getCarbohydrates());
        // cоздание обьекта через Builder pattern
        Dish dish = Dish.builder()
                .name(dto.getName())
                .nutritionStats(stats)
                .build();
        dishRepository.save(dish);
    }


    private void validateData(DishDTO dto){
        if(dto.getFat() < 0 || dto.getCarbohydrates() < 0 || dto.getProtein() < 0 || dto.getCaloriesPerServing() < 0){
            throw new IllegalArgumentException("Nutrition stats can't be negative");
        }
        checkIfExist(dto.getName());
    }

    private void checkIfExist(String name){
        dishRepository.findByName(name)
                .ifPresent(o -> {
                    throw new IllegalArgumentException("You already add this type of dish");
                });
    }
}
