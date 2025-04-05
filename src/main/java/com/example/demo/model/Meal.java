package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="meals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "meals", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes = new ArrayList<>();
}
