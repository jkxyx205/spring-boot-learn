package com.rick.spring.mvc.controller;

import com.rick.spring.mvc.model.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rick
 * @createdAt 2021-10-25 15:55:00
 */
@RestController
public class SimpleController {

    @ModelAttribute("nickname")
    public String init(Model model) {
        System.out.println("Controller: SimpleController init 执行");
        model.addAttribute("postCode", "225431");
        return "Rick";
    }

    @GetMapping("request")
    public Map<String, Object> request(@Validated User user,
//                                   BindingResult bindingResult, //必须紧跟@Valid对象后面
                                   @RequestParam(required = false) @Valid @Min(18) Integer age,
                                   @RequestParam Map<String, Object> requestParamMap,
                                   @RequestParam("names") List<String> namesList,
                                   String[] names,
                                   @ModelAttribute("postCode") String postCode,
                                   @ModelAttribute("nickname") String nickname,
                                   Map<String, Object> modelMap,
                                   Model model,
                                   HttpServletRequest request) {
        System.out.println("Controller: request 执行");

        Map<String, Object> resultMap = new HashMap<>();
        // 将所有的请求参数值放到requestParamMap中，如果参数是数组，只放第一个
        resultMap.put("requestParamMap", requestParamMap);
        resultMap.put("namesList", namesList);
        resultMap.put("names", names);

        resultMap.put("user", user);

        resultMap.put("age", age);
        resultMap.put("postCode", postCode);
        resultMap.put("nickname", nickname);
//        resultMap.put("bindingResult", bindingResult.getAllErrors());

        modelMap.put("hobby", "swimming");
        model.addAttribute("sex", "男");
        request.setAttribute("home", "TaiXing");
        return resultMap;
    }
}
