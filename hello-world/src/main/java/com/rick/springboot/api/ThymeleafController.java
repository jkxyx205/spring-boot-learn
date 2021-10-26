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
import java.util.Enumeration;
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
     * Model／Map的数据会自动加入的Request Scope中，这样页面就能拿到这个范围的数据。将model放到Request中不是Spring MVC 完成的，而是由视图去完成的。
     *  DispatcherServlet#render的时候，view.render(mv.getModelInternal(), request, response); 会将Model传给视图，视图（Thymeleaf）拿到Model放到上下文中。
     *  WebEngineContext 中 this.requestAttributesVariablesMap = new WebEngineContext.RequestAttributesVariablesMap(configuration, templateData, templateResolutionAttributes, this.request, locale, variables);
     *  RequestAttributesVariablesMap构造函数将 variables 放入Request中
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
        map.put("eq", map == model.asMap());
        // 执行这个，才能将所有参数放到model中
//        map.putAll(requestMap);
        System.out.println(map == model.asMap());
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println("attributeName =>" + attributeNames.nextElement());
        }

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
