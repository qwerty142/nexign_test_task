package org.example.configs;

import org.example.generator.CdrGenerator;
import org.example.repository.repositories.CdrRepository;
import org.example.repository.repositories.UserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApplicationConfig.class)
@ComponentScan(basePackages = {"org.example"})
@ConditionalOnProperty(prefix = "app", name = "dogeneration", havingValue = "true")
public class GenerationConfig {

    @Bean
    public CdrGenerator createGeneration(UserRepository userRepository, CdrRepository cdrRepository) {
        return new CdrGenerator(cdrRepository, userRepository);
    }

}
