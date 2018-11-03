package com.example.webDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class WebDemoApplication {
    
    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World!";
        
    }

	public static void main(String[] args) {
		SpringApplication.run(WebDemoApplication.class, args);
	}
	

}
