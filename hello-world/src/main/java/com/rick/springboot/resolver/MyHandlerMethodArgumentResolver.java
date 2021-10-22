package com.rick.springboot.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Rick
 * @createdAt 2021-10-21 14:47:00
 */
public class MyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType() == int.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        System.out.println("MethodArgumentResolver: MyHandlerMethodArgumentResolver 执行");
        String parameterName = methodParameter.getParameterName();
        String value = nativeWebRequest.getParameter(parameterName);
        if (StringUtils.hasText(value)) {
            return Integer.parseInt(value);
        }

        return null;
    }
}
