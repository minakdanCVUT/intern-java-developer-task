package com.example.demo.dto;

import com.example.demo.enums.GoalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String email;
    private boolean isMale;
    private int age;
    private double weight;
    private int height;
    private GoalType type;
}
