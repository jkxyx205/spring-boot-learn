package com.rick.springboot.api;

import com.rick.common.http.web.annotation.UnWrapped;
import com.rick.springboot.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    /**
     * 优先级 ModelAndView > @ResponseBody > ViewName
     * @return
     */
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

    @GetMapping("view3")
    public Model view3(Model model) {
        model.addAttribute("q", "q");
        return model;
    }

    @GetMapping("view4")
    @ModelAttribute("s")
    public String modelAttributeView() {
        return "sqr";
    }

    @GetMapping("exception")
    @ModelAttribute("s")
    public String exception() {
        int a  = 1 / 0;
        return "sqr";
    }

    @GetMapping(value = "view5")
    public String view5() {
        return "view5";
    }


    @GetMapping(value = "view6")
    public User view6() {
        return new User();
    }

    @GetMapping(value = "view7")
    @UnWrapped
    public User view7() {
        return new User();
    }
}
