package com.example.demo.dto;

import com.example.demo.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealDTO {
    private LocalDate date;
    private MealType type;
    private List<String> dishNames;
}
