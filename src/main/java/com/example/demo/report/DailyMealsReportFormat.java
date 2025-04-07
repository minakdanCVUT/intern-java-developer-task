package com.example.demo.report;


import com.example.demo.model.NutritionStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyMealsReportFormat {
    private LocalDate date;
    private NutritionStats stats;
    private List<MealReportFormat> meals = new ArrayList<>();
}
