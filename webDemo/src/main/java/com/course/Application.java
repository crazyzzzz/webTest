package com.course;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Application {
    
    private static ConfigurableApplicationContext contxt; 

    public static void main(String[] args) {
        Application.contxt = SpringApplication.run(Application.class,args);
    }
    
    @PreDestroy
    public void close() {
        Application.contxt.close();
    }
}
