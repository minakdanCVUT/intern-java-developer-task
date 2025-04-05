package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDTO dto) {
        validateData(dto);
        User user = new User(dto.isMale(), dto.getName(),
                dto.getEmail(), dto.getAge(), dto.getWeight(), dto.getHeight(), dto.getType());
        user.setNormalCaloriesCount(calculateBMR(dto));
        userRepository.save(user);
    }

    private double calculateBMR(UserDTO user) {
        if (user.isMale()) {
            return 88.362
                    + (13.397 * user.getWeight())
                    + (4.799 * user.getHeight())
                    - (5.677 * user.getAge());
        } else {
            return 447.593
                    + (9.247 * user.getWeight())
                    + (3.098 * user.getHeight())
                    - (4.330 * user.getAge());
        }
    }

    private void validateData(UserDTO dto){
        if(dto.getAge() < 0 || dto.getAge() > 110){
            throw new IllegalArgumentException("Enter valid age");
        }
        if(!dto.getName().matches("^[a-zA-Zа-яА-ЯёЁ]+$")){
            throw new IllegalArgumentException("Enter a valid name");
        }
        if(dto.getWeight() < 2d || dto.getWeight() > 350d){
            throw new IllegalArgumentException("Enter a valid weight");
        }
        if(dto.getHeight() < 35 || dto.getHeight() > 250){
            throw new IllegalArgumentException("Enter a valid height");
        }
    }
}
