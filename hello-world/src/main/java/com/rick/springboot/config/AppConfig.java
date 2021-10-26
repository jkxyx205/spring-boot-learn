package com.rick.springboot.config;

import com.rick.common.http.exception.ApiExceptionHandler;
import com.rick.common.http.web.config.EnableResultWrapped;
import com.rick.common.validate.ServiceMethodValidationInterceptor;
import com.rick.common.validate.ValidatorHelper;
import com.rick.springboot.model.Cat;
import com.rick.springboot.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.validation.Validator;


/**
 * @author Rick
 * @createdAt 2021-10-20 13:45:00
 */
@Configuration(proxyBeanMethods = true)
@Import({ApiExceptionHandler.class, ServiceMethodValidationInterceptor.class})
@EnableResultWrapped
@RequiredArgsConstructor
public class AppConfig {

    private final Validator validator;

    @Bean
    public ValidatorHelper validatorHelper() {
        return new ValidatorHelper(validator);
    }

    @Bean
    public User user() {
        return new User(cat());
    }

    @Bean
    public Cat cat() {
        return new Cat();
    }
}
