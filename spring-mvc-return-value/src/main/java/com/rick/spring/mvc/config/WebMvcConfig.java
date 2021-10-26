package com.rick.spring.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Rick
 * @createdAt 2021-10-26 15:50:00
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(new HandlerMethodReturnValueHandler() {

            @Override
            public boolean supportsReturnType(MethodParameter returnType) {
                return Integer.class == returnType.getParameterType();
            }

            @Override
            public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
                Integer value = (Integer) returnValue;
                mavContainer.addAttribute("intValue", value);
                if (value > 10) {
                    mavContainer.setViewName("big");
                } else {
                    mavContainer.setViewName("small");
                }
            }
        });
    }
}
