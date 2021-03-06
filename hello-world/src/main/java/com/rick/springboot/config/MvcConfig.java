package com.rick.springboot.config;

import com.rick.springboot.resolver.MyHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.ControllerAdviceBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rick
 * @createdAt 2021-10-21 12:44:00
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

//    @Autowired
//    private MyAdvice advice;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                System.out.println("Interceptor: HandlerInterceptor preHandle ??????");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                System.out.println("Interceptor: HandlerInterceptor postHandle ??????");
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                System.out.println("Interceptor: HandlerInterceptor afterCompletion ??????");
            }
        });

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MyHandlerMethodArgumentResolver());
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        // ?????????Handler??????
//        handlers.add(myHandlerMethodReturnValueHandler());
    }

//    @Autowired
//    public void setMyHandlerMethodReturnValueHandler() {
//        List<HandlerMethodReturnValueHandler> defaultHandlerMethodReturnValueHandler = requestMappingHandlerAdapter.getReturnValueHandlers();
//
//        List<HandlerMethodReturnValueHandler> resolvers = new ArrayList<>();
//        resolvers.addAll(defaultHandlerMethodReturnValueHandler);
//      // ???????????????Handler??????????????????????????????RequestResponseBodyMethodProcessor?????????
//        resolvers.add(11, myHandlerMethodReturnValueHandler());
//        requestMappingHandlerAdapter.setReturnValueHandlers(resolvers);
//    }

    private HandlerMethodReturnValueHandler myHandlerMethodReturnValueHandler() {
        return new HandlerMethodReturnValueHandler() {

            private HandlerMethodReturnValueHandler handler; //new ViewNameMethodReturnValueHandler();

            // ??????RequestResponseBodyMethodProcessor??????
            {
                // converters
                List<HttpMessageConverter<?>> converters = new ArrayList<>();
                converters.addAll(requestMappingHandlerAdapter.getMessageConverters());

                // advice
                List<Object> advice  = new ArrayList<>();
                List<ControllerAdviceBean> adviceBeans = ControllerAdviceBean.findAnnotatedBeans(applicationContext);
                for (ControllerAdviceBean adviceBean : adviceBeans) {
                    Class<?> beanType = adviceBean.getBeanType();
                    if (beanType == null) {
                        throw new IllegalStateException("Unresolvable type for ControllerAdviceBean: " + adviceBean);
                    }
                    advice.add(adviceBean);
                }

                handler = new RequestResponseBodyMethodProcessor(converters, advice);
            }

            @Override
            public boolean supportsReturnType(MethodParameter methodParameter) {
                return handler.supportsReturnType(methodParameter);
            }

            @Override
            public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
//                Object wrapperObject = (o instanceof Result || methodParameter.hasMethodAnnotation(NotWrapped.class)) ? o : ResultUtils.success(o);
//                handler.handleReturnValue(wrapperObject, methodParameter, modelAndViewContainer, nativeWebRequest);
                // AbstractMessageConverterMethodProcessor ????????????ResponseBody????????????
                // genericConverter.write(body, targetType, selectedMediaType, outputMessage);
                // genericConverter <- MappingJackson2HttpMessageConverter
//                System.out.println("MethodReturnValueHandler: HandlerMethodReturnValueHandler ?????? " + o);
            }
        };
    }



    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
                System.out.println("Filter: filterRegistration ?????? ????????????");
                chain.doFilter(request, response);
                System.out.println("Filter: filterRegistration ?????? ????????????");
            }
        });
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("filterRegistration");
        registration.setOrder(Integer.MIN_VALUE);
        return registration;
    }
}
