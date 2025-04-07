package com.example.demo.report;

import com.example.demo.model.NutritionStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishReportFormat {
    private String name;
    private NutritionStats stats;
}
