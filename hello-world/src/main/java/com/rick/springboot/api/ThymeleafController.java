package com.rick.springboot.api;

import com.rick.springboot.model.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author Rick
 * @createdAt 2021-10-21 09:56:00
 */
@Controller
public class ThymeleafController {

    /**
     *
     * http://localhost:8080/th?name=Jim&age=100&address=SuZhou&code=2323
     * PersonForm 会自动放到Model中
     * @param personForm
     * @param requestMap
     * @param map
     * @param model
     * @param request
     * @return
     */
    @GetMapping("th")
    public String view(PersonForm personForm,
                       @RequestParam Map<String, String> requestMap,
                       @ModelAttribute String code, // 从model中获取数据，如果传了参数code，也不会拿！
                       Map<String, Object> map, // map和下面的model是一个东西
                       Model model,
                       HttpServletRequest request) {
        map.put("hello", "world");
        model.addAttribute("world", "hello");
        request.setAttribute("age", 32);
        personForm.setAge(100);

        // 执行这个，才能将所有参数放到model中
        System.out.println(map == model.asMap());
//        map.putAll(requestMap);
        map.put("eq", map == model.asMap());
        return "index";
    }

    @GetMapping("/form")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/form")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
        // 放到model中了
        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/index.html";
    }

}
