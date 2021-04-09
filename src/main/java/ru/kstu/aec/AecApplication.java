package ru.kstu.aec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AecApplication {
    public static void main(String[] args) {
        SpringApplication.run(AecApplication.class, args);
    }
}
