package com.rick.springboot.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Rick
 * @createdAt 2021-10-21 09:56:00
 */
@RestController
@RequestMapping
public class ViewController {

    @GetMapping("view")
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("forward:index.html");
        return mv;
    }

    @GetMapping("view2")
    public ModelAndView view2() {
        ModelAndView mv = new ModelAndView();

        mv.setView(new View() {

            @Override
            public String getContentType() {
                return "application/json;charset=UTF-8";
            }

            @Override
            public void render(Map<String, ?> map, HttpServletRequest request, HttpServletResponse response) throws IOException {
                response.setContentType(getContentType());
                response.getWriter().write("{\"hello\": \"view\"}");
            }
        });
        return mv;
    }

}
