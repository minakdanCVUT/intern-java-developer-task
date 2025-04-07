package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MealRepositoryTest {
    @Autowired
    private MealRepository mealRepository;
}
