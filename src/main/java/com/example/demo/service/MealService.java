package com.example.demo.service;

import com.example.demo.dto.MealDTO;
import com.example.demo.enums.MealType;
import com.example.demo.model.*;
import com.example.demo.repository.DailyMealsRepository;
import com.example.demo.repository.DishRepository;
import com.example.demo.repository.MealRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final DishRepository dishRepository;
    private final DailyMealsRepository dailyMealsRepository;
    private final UserRepository userRepository;

    public MealService(MealRepository mealRepository, DishRepository dishRepository, DailyMealsRepository dailyMealsRepository, UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.dishRepository = dishRepository;
        this.dailyMealsRepository = dailyMealsRepository;
        this.userRepository = userRepository;
    }

    public void createMeal(MealDTO dto, String name){
        User user = userRepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("No user with this username")
        ); // уже нашли пользователя, если нет такого dailymeals - создаем

        // получаем из бд или создаем новый dailymeal на полученную дату
        DailyMeals dailyMeals = getOrCreateDailyMeals(dto.getDate(), user);

        // проверка если уже был такой прием пищи за эту дату
        checkMealsType(dto.getType(), dailyMeals);

        // создание нового обьекта для приема пищи
        Meal meal = createMealObject(dto.getType(), dailyMeals);

        // создаем связи приема пищи и блюд посредством дополнительной M:N таблицы
        fillDishMealRelations(dto.getDishNames(), meal);

        // добавляем данный прием пищи в список всех приемов пищи за эту дату
        dailyMeals.getMeals().add(meal);
        dailyMeals.getNutritionStats().addStats(meal.getNutritionStats());

        mealRepository.save(meal);
    }

    private DailyMeals getOrCreateDailyMeals(LocalDate date, User user){
        return dailyMealsRepository.getByDateAndUser(date, user).orElseGet(
                () -> dailyMealsRepository.save(DailyMeals.builder()
                        .date(date)
                        .user(user)
                        .meals(new ArrayList<>())
                        .nutritionStats(new NutritionStats())
                        .build())
        );
    }

    private Meal createMealObject(MealType type, DailyMeals dailyMeals){
        return Meal.builder()
                .type(type)
                .dishMeals(new ArrayList<>())
                .dailyMeals(dailyMeals)
                .nutritionStats(new NutritionStats())
                .build();
    }

    private void checkMealsType(MealType type, DailyMeals dailyMeals){
        if(!type.equals(MealType.OTHER)){
            if(dailyMeals.getMeals() == null){
                dailyMeals.setMeals(new ArrayList<>());
            }else{
                for(Meal meal : dailyMeals.getMeals()){
                    if(meal.getType().equals(type)){
                        throw new IllegalArgumentException("You can't add this type more than once");
                    }
                }
            }
        }
    }

    private void fillDishMealRelations(List<String> dishNames, Meal meal){
        for(String dishName : dishNames){
            Dish dish = dishRepository.findByName(dishName).orElseThrow(
                    () -> new IllegalArgumentException("We don't have this dish in out system")
            );
            DishMeal dishMeal = new DishMeal(dish, meal);
            dish.getDishMeals().add(dishMeal);
            meal.getDishMeals().add(dishMeal);
            // складываем калории и бжу
            meal.getNutritionStats().addStats(dish.getNutritionStats());
        }
    }
}
