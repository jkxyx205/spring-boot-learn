package com.rick.springboot.mapping;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Rick
 * @createdAt 2021-10-21 15:35:00
 */
public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private static final Pattern VERSION_PREFIX_PATTERN = Pattern.compile("/(v\\d+)/");

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        if (apiVersion == null) {
            return null;
        }

        return new RequestCondition() {

            @Override
            public Object combine(Object o) {
                return o;
            }

            @Override
            public Object getMatchingCondition(HttpServletRequest httpServletRequest) {
                Matcher m = VERSION_PREFIX_PATTERN.matcher(httpServletRequest.getRequestURI());
                if (m.find()) {
                    String pathVersion = m.group(1);
                    if (Objects.equals(pathVersion, apiVersion.value())) {
                        return this;
                    }
                }
                return null;
            }

            @Override
            public int compareTo(Object o, HttpServletRequest httpServletRequest) {
                return 0;
            }
        };
    }
}
