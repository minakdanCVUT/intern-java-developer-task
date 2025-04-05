package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="daily_meals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyMeals {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @OneToMany(mappedBy = "dailyMeals", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="users_id")
    private User user;

    @Embedded
    private NutritionStats nutritionStats;
}
