package com.example.demo.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class MainController {
    
    @PostMapping("/hello")
    public ModelAndView hello(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String data = request.getParameter("id");
        String data_type = request.getParameter("data_type");
        System.out.println(data);
        System.out.println(data_type);
        mv.setViewName("hello");
        return mv;
    }
}
