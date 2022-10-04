package me.junkxxl.testing.testapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig {
    @Value("${spring.datasource.URL}")
    public String URL;
    public ObjectMapper objectMapper;

    @Bean
    void getMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
    }
}
