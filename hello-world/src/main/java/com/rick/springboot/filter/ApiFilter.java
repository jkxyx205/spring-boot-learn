package com.rick.springboot.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rick
 * @createdAt 2021-10-21 12:04:00
 */
@Component("myApiFilter")
public class ApiFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("Filter: myApiFilter 执行之前");
        chain.doFilter(request, response);
        System.out.println("Filter: myApiFilter 执行之后");
    }
}
