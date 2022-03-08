package com.example.demo.resources;

import com.example.demo.process.Properties;
import com.example.demo.process.StaticItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class Demo {
    @Autowired
    private Properties properties;

    @GetMapping
    public String getDadas(){
//        return properties.toString();
        return StaticItems.publicProperties.toString();
    }
    @GetMapping("/item")
    public String getItem(){
        return this.properties.item().toString();
    }
}
