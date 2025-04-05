package com.example.demo.dto;

import com.example.demo.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MealDTO {
    private Date date;
    private MealType type;
    private List<String> dishNames;
}
