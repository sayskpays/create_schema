package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    
    @PostMapping("/hello")
    public ModelAndView hello(HttpServletRequest request, ModelAndView mv) {
        
        // 폼에서 전달해주는 input data
        String data = request.getParameter("id");
        // 폼에서 전달해주는 data type 
        String data_type = request.getParameter("data_type");

         /* 
         생성시켜야 할 데이터 갯수(라인) count
         */
         int count = 1;
         char[] words= data.toCharArray();
         for(int i=0; i<words.length;i++){
             if(words[i]=='\n'){
                 count++;
             }
         }
        /* 
        들어오는 create query 데이터 가공 {\n} 분기별로 한 줄씩 데이터 저장
        */ 
        String[] dataColumn = data.split("\n");
        String form_varchar = "varchar"; 
        String type_number = "number"; 
        String type_not_null = "not null"; 
        String type_nill = "nill"; 

        for(int i=0; i < dataColumn.length; i++){
            if(dataColumn[i].contains("NOT") && dataColumn[i].contains("VARCHAR2")){
                String xml_data = String.format("<xs:element name=''\tform='%s'\ttype='%s'\t/>" , form_varchar, type_not_null);       
            }else if(dataColumn[i].contains("NOT") && dataColumn[i].contains("VARCHAR2")){
                String xml_data = String.format("<xs:element name=''\tform='%s'\ttype='%s'\t/>" , form_varchar, type_not_null);
            }
        }
        
        // for(String a:dataColumn){
        //     System.out.println(a);
        //     if(a.contains("NOT")){
        //         String xml_data = String.format("<xs:element name=''\tform='%s'\ttype='%s'\t/>" , form, type);

        //     }
        // }

        //if(data_type=="schema"){

        // count 개수에 맞춰서 xml 기본 형식 데이터 생성
        //  String normal_xml_data = xml_data.repeat(count);
        //  mv.addObject("xml_data", normal_xml_data.replace("/>", "/>\r\n"));

        //}
        //mv.addObject("xml_data",dataList);
        mv.setViewName("hello");
        return mv;
    }
}
