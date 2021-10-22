package com.rick.springboot.mapping;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 没有 implements WebMvcRegistrations，只是注解@Component 会丢失拦截器等信息
 * @author Rick
 * @createdAt 2021-10-21 15:35:00
 */
//@Component
public class MyHandlerMapping extends RequestMappingHandlerMapping implements Ordered {

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class) || AnnotatedElementUtils.hasAnnotation(beanType, MyMapping.class);
    }

    @Override
    protected void handleMatch(RequestMappingInfo info, String lookupPath, HttpServletRequest request) {
        System.out.println("MyHandlerMapping handleMatch: 执行");
        super.handleMatch(info, lookupPath, request);
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        MyMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, MyMapping.class);
        if (requestMapping == null) {
            return null;
        }

        String version = requestMapping.version();

        RequestCondition<?> versionCondition = new RequestCondition() {

            @Override
            public Object combine(Object o) {
                return o;
            }

            @Override
            public Object getMatchingCondition(HttpServletRequest request) {
                // 自定义映射匹配的条件
                String paramVersion = request.getParameter("version");
                if (!StringUtils.hasText(version) && !StringUtils.hasText(paramVersion)) {
                    return this;
                } else if(StringUtils.hasText(version) && StringUtils.hasText(paramVersion) && version.equals(paramVersion)) {
                    return this;
                }

                return null;
            }

            @Override
            public int compareTo(Object o, HttpServletRequest httpServletRequest) {
                return 0;
            }
        };
        return RequestMappingInfo.paths(requestMapping.value()).customCondition(versionCondition).build();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
