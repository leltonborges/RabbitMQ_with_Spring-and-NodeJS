package com.example.demo.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StaticItems {
    public static Properties publicProperties;

    @Autowired
    private Properties properties;

    @PostConstruct
    public void init(){
        StaticItems.publicProperties =  this.properties;
    }

}
