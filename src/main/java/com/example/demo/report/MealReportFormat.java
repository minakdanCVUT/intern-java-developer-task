package com.example.demo.report;

import com.example.demo.enums.MealType;
import com.example.demo.model.NutritionStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealReportFormat {
    private MealType type;
    private NutritionStats stats;
    private List<DishReportFormat> dishes = new ArrayList<>();
}
