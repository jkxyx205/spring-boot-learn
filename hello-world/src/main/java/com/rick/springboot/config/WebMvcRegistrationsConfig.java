package com.rick.springboot.config;

import com.rick.springboot.mapping.MyHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author Rick
 * @createdAt 2021-10-21 16:01:00
 */
//@Configuration
public class WebMvcRegistrationsConfig implements WebMvcRegistrations {

    /**
     * RequestMappingHandlerMapping 被 VersionRequestMappingHandlerMappingHandlerMapping 替换
     * 按照VersionRequestMappingHandlerMappingHandlerMapping映射逻辑进行映射
     * @return
     */
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//        return new VersionRequestMappingHandlerMapping();
        return new MyHandlerMapping();
    }
}
