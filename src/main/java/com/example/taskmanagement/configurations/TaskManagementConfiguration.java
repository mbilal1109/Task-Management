package com.example.taskmanagement.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskManagementConfiguration {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
