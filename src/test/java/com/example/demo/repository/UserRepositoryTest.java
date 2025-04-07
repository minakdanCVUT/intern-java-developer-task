package com.example.demo.repository;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByName_ShouldReturnUser(){
        User user = User.builder().name("John").build();
        userRepository.save(user);

        User found = userRepository.findByName("John").orElseThrow();
        assertThat(found.getName()).isEqualTo("John");
    }
}
