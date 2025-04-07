package com.example.demo.repository;

import com.example.demo.model.DailyMeals;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyMealsRepository extends JpaRepository<DailyMeals, Long> {
    Optional<DailyMeals> getByDateAndUser(LocalDate date, User user);
}
