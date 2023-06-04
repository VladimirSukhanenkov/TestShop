package com.example.springsecurity.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Нужен для сканирования пэкэджа util. Добавлена аннтотация @Component в PersonalValidator = работает без этого класса конфиграции
@Configuration
@ComponentScan("com.example.springsecurity.util")
public class SpringConfig {
}

