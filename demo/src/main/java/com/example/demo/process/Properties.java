package com.example.demo.process;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "amqp")
@Data
public class Properties {
    private String exchange;
    private Queue queue;

    @Bean
    @ConfigurationProperties(prefix = "item")
    public Item item(){
        return new Item();
    }

}
