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
        
        ModelAndView mv = new ModelAndView();
        String name = "EAI_TR_DT";
        String form = "unqualified";
        String type = "string";
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
        //String 생성해놓고 ArrayList 또는 String 배열로 관리
        ArrayList<String> dataList = new ArrayList<>();

        // 들어오는 데이터에 따라서 여기서 조건 분기로 xml data 나눠주는 방법?
        for(int i=0;i<count;i++){
            String xml_data = String.format("<xs:element name='%s'\tform='%s'\ttype='%s'\t/>", name , form, type);
            dataList.add(xml_data);
        }
        
        //if(data_type=="schema"){

        // count 개수에 맞춰서 xml 기본 형식 데이터 생성
        //  String normal_xml_data = xml_data.repeat(count);
        //  mv.addObject("xml_data", normal_xml_data.replace("/>", "/>\r\n"));

        //}

        mv.addObject("xml_data",dataList);
        mv.setViewName("hello");
        return mv;
    }
}
