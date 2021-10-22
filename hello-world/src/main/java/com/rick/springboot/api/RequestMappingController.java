package com.rick.springboot.api;

import com.rick.springboot.mapping.MyMapping;
import com.rick.springboot.model.User;
import com.rick.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rick
 * @createdAt 2021-10-21 09:56:00
 */
@RestController
@RequestMapping
//@Validated
public class RequestMappingController {

    @Autowired
    private UserService userService;

    @ModelAttribute("nickname")
    public String init(Model model) {
        System.out.println("Controller: RequestMappingController init 执行");
        model.addAttribute("postCode", "225431");
        return "Rick";
    }

    /**
     * Map参数的@RequestParam是必须的，数组只能获取一个
     * http://localhost:8080/req?age=1&names=%E5%BC%A0%E4%B8%89&names=%E6%9D%8E%E5%9B%9B&version=v2
     * http://localhost:8080/req?age=1&names=%E5%BC%A0%E4%B8%89,%E6%9D%8E%E5%9B%9B&version=v2
     * @ModelAttribute String address从ModelAttribute获取值，从请求参数获取不到值！
     *
     * "forward:/success" 在@Controller中才能跳转，@RestController中显示字符串"forward:/success"
     *
     * Springmvc对验证做了支持。@Valid/@Validated 参数是「对象」，如果要收集异常，那么后面立即紧跟BindingResult
     *          源代码查看ModelAttributeMethodProcessor => validateIfApplicable(binder, parameter)
     *                                                => Object[] validationHints = ValidationAnnotationUtils.determineValidationHints(ann);
     * @Min(18) Integer age，必须在类上加入注解@Validated，错误信息但是不会放到bindingResult，这个不是有springmvc去处理的，而是`MethodValidationPostProcessor` 拦截器
     * @return
     */
    @GetMapping("req")
//    @MyMapping(value = "req", version = "v1")
    public Map<String, Object> req(@Validated User user,
                                   BindingResult bindingResult, //必须紧跟@Valid对象后面
                                   @RequestParam(required = false) @Min(18) Integer age,
                                   @RequestParam Map<String, Object> requestParamMap,
                                   @RequestParam("names") List<String> namesList,
                                   String[] names,
                                   @ModelAttribute String address,
                                   @ModelAttribute String postCode,
                                   @ModelAttribute String nickname,
                                   Map<String, Object> modelMap,
                                   Model model,
                                   HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("requestParamMap", requestParamMap);
        resultMap.put("namesList", namesList);
        resultMap.put("user", user);
        resultMap.put("names", names);
        resultMap.put("age", age);
        resultMap.put("address", address);
        resultMap.put("postCode", postCode);
        resultMap.put("nickname", nickname);
        resultMap.put("bindingResult", bindingResult.getAllErrors());

        modelMap.put("hobby", "swimming");
        model.addAttribute("sex", "男");
        request.setAttribute("home", "TaiXing");

        System.out.println("Controller: RequestMappingController get 执行");
        return resultMap;
//        return "forward:/success";
    }

    @MyMapping(value = "req")
    public String get2(@RequestParam("names") List<String> namesList, String[] names, int age, @RequestParam Map<String, Object> params) {
        return "default";
    }

    @MyMapping(value = "req",  version = "v2")
    public String get3(@RequestParam("names") List<String> namesList, String[] names, int age, @RequestParam Map<String, Object> params) {
        return "v2";
    }

    /**
     * 没有返回Map<String, Object>对象JSON还是去找页面
     * @param modelMap
     * @param model
     * @param home
     * @return
     */
    @GetMapping("success")
    @ResponseBody
    public String success(Map<String, Object> modelMap, Model model, @RequestAttribute("home") String home) {
        return "success";
    }

    @RequestMapping("success2")
    @ModelAttribute("hello")
    public String successPage(User user, @RequestParam(required = false) Integer age, String title) {
        userService.save(user, age);
        userService.save2(title);
        return "hi";
    }

}
