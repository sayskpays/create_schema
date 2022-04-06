package com.example.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    
    @PostMapping("/hello")
    public ModelAndView hello(HttpServletRequest request) {
        
        String name = "EAI_TR_DT";
        String form = "unqualified";
        String type = "string";
        String xml_data = String.format("<xs:element name='%s'\tform='%s'\ttype='%s'\t/>", name , form, type);
        // 폼에서 전달해주는 input data
        String data = request.getParameter("id");
        // 폼에서 전달해주는 data type 
        String data_type = request.getParameter("data_type");
    
        // 생성시켜야 할 데이터 갯수(라인) count
        int count = 1;
        char[] words= data.toCharArray();
        for(int i=0; i<words.length;i++){
            if(words[i]=='\n'){
                count++;
            }
        }
        
        // count 개수에 맞춰서 xml 기본 형식 데이터 생성
        String normal_xml_data = xml_data.repeat(count);
        // '>' 문자를 만나면 개행 후 저장
        // char[] normal = normal_xml_data.toCharArray();
        // for(int i=0; i<normal_xml_data.length();i++){
        //     if(normal[i] == '>'){
        //        String temp =  normal_xml_data.replace("\r\n", "<br>");
        //     }
        // }
        // System.out.println(normal_xml_data);   

        ModelAndView mv = new ModelAndView();
        mv.addObject("xml_data", normal_xml_data.replace("/>", "/>\r\n"));

        mv.setViewName("hello");
        return mv;
    }
}
