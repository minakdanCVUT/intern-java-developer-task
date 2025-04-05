package com.example.demo.model;

import com.example.demo.enums.GoalType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isMale;

    private String name;

    private String email;

    private int age;

    private double weight;

    private int height;

    private GoalType type;

    private double normalCaloriesCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DailyMeals> dailyMeals = new ArrayList<>();

    public User(boolean isMale, String name, String email, int age, double weight, int height, GoalType type) {
        this.isMale = isMale;
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.type = type;
    }
}
