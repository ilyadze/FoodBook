package com.example.foodbook;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class FoodBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodBookApplication.class, args);
    }


}
