package com.example.foodbook.configurations;

import com.example.foodbook.mapper.LocalMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public LocalMapper localMapper(  ModelMapper modelMapper){
        return new LocalMapper(modelMapper);
    }
}
