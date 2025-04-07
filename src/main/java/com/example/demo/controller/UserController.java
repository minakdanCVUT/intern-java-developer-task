package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.report.DailyMealsReportFormat;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO dto){
        userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/calories/control")
    public ResponseEntity<Boolean> controlCalories(
            @RequestBody LocalDate date,
            @RequestParam String name){
        return ResponseEntity.ok(userService.controlCalories(date, name));
    }

    @GetMapping("/report")
    public ResponseEntity<DailyMealsReportFormat> getReportForTheDay(
            @RequestParam LocalDate date,
            @RequestParam String name
    ){
        DailyMealsReportFormat format = userService.getReportForTheDay(date, name);
        return ResponseEntity.status(HttpStatus.OK).body(format);
    }
}
