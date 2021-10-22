package com.rick.springboot;

import com.rick.springboot.config.AppConfig;
import com.rick.springboot.model.Cat;
import com.rick.springboot.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Rick
 * @createdAt 2021-10-19 21:09:00
 */
@SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HelloWorldApplication.class, args);

        // Test
        System.out.println(context.getBean(AppConfig.class));
        System.out.println(context.getBean(User.class).getCat() == context.getBean(Cat.class));

    }
}
