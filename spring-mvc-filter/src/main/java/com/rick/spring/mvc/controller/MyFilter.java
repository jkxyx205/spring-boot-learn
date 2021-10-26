package com.rick.spring.mvc.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rick
 * @createdAt 2021-10-26 15:42:00
 */
@Component
public class MyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter: MyFilter 执行之前");
        chain.doFilter(request, response);
        System.out.println("Filter: MyFilter 执行之后");
    }

}