package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.*;
import com.example.demo.report.DailyMealsReportFormat;
import com.example.demo.report.DishReportFormat;
import com.example.demo.report.MealReportFormat;
import com.example.demo.repository.DailyMealsRepository;
import com.example.demo.repository.MealRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DailyMealsRepository dailyMealsRepository;
    private final MealRepository mealRepository;

    public UserService(UserRepository userRepository, DailyMealsRepository dailyMealsRepository, MealRepository mealRepository) {
        this.userRepository = userRepository;
        this.dailyMealsRepository = dailyMealsRepository;
        this.mealRepository = mealRepository;
    }


    public void createUser(UserDTO dto) {
        userRepository.findByName(dto.getName()).ifPresent(user -> {
            throw new EntityExistsException("User with this name already exists");
        });
        validateData(dto);
        User user = new User(dto.isMale(), dto.getName(),
                dto.getEmail(), dto.getAge(), dto.getWeight(), dto.getHeight(), dto.getType());
        user.setNormalCaloriesCount(calculateBMR(dto));
        userRepository.save(user);
    }

    private double calculateBMR(UserDTO user) {
        if (user.isMale()) {
            return 88.362
                    + (13.397 * user.getWeight())
                    + (4.799 * user.getHeight())
                    - (5.677 * user.getAge());
        } else {
            return 447.593
                    + (9.247 * user.getWeight())
                    + (3.098 * user.getHeight())
                    - (4.330 * user.getAge());
        }
    }

    private void validateData(UserDTO dto){
        if(dto.getAge() < 0 || dto.getAge() > 110){
            throw new IllegalArgumentException("Enter valid age");
        }
        if(!dto.getName().matches("^[a-zA-Zа-яА-ЯёЁ0-9]+$")){
            throw new IllegalArgumentException("Enter a valid name");
        }
        if(dto.getWeight() < 2d || dto.getWeight() > 350d){
            throw new IllegalArgumentException("Enter a valid weight");
        }
        if(dto.getHeight() < 35 || dto.getHeight() > 250){
            throw new IllegalArgumentException("Enter a valid height");
        }
    }

    public boolean controlCalories(LocalDate date, String name) {
        User user = getUser(name);
        DailyMeals dailyMeals = getDailyMeals(date, user);
        return user.getNormalCaloriesCount() <= dailyMeals.getNutritionStats().getCalories();
    }


    public DailyMealsReportFormat getReportForTheDay(LocalDate date, String name) {
        User user = getUser(name);
        System.out.println(date);
        DailyMeals mealsByDate = getDailyMeals(date, user);
        DailyMealsReportFormat format = DailyMealsReportFormat.builder()
                .date(date)
                .stats(new NutritionStats())
                .meals(getMealReportFormat(mealsByDate))
                .build();
        format.getStats().addStats(mealsByDate.getNutritionStats());
        return format;
    }

    private List<MealReportFormat> getMealReportFormat(DailyMeals dailyMeals){
        List<MealReportFormat> meals = new ArrayList<>();
        for(Meal meal : dailyMeals.getMeals()){
            meals.add(MealReportFormat.builder()
                    .type(meal.getType())
                    .dishes(getDishReportFormat(meal))
                    .stats(new NutritionStats())
                    .build());
            meals.getLast().getStats().addStats(meal.getNutritionStats());
        }
        return meals;
    }

    private List<DishReportFormat> getDishReportFormat(Meal meal){
        List<DishReportFormat> dishes = new ArrayList<>();
        for(Dish dish : mealRepository.getDishesByMeal(meal.getId())){
            dishes.add(DishReportFormat.builder()
                    .stats(new NutritionStats())
                    .name(dish.getName())
                    .build());
            dishes.getLast().getStats().addStats(dish.getNutritionStats());
        }
        return dishes;
    }


    private User getUser(String name){
        return userRepository.findByName(name).orElseThrow(
                () -> new IllegalArgumentException("No user with this username")
        );
    }

    private DailyMeals getDailyMeals(LocalDate date, User user){
        return dailyMealsRepository.getByDateAndUser(date, user).orElseThrow(
                () -> new IllegalArgumentException("For your username dont exist daily meal for this date")
        );
    }
}
