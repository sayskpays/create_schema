package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        들어오는 create query 데이터 가공 {,} 분기별로 한 줄씩 데이터 저장 후 다시 list에 저장
        */ 
        String[] dataColumn = data.split(",");
         
        ArrayList<String> test01 = new ArrayList<>();
        ArrayList<String[]> stData = new ArrayList<>();

        
        for(int i=0; i < dataColumn.length; i++){
            test01.add(dataColumn[i]);
            stData.add(test01.get(i).split("\"\""));
        }
        
        for(String[] a : stData){
            System.out.println("String data : " + a[0]);
        }

        System.out.println(test01.get(0));

        //    String[] columnStringData = dataColumn[i].split("\"\"");
        
        /* "EAI_INS_CD" VARCHAR2(7 BYTE) NOT NULL ENABLE 
            "" 안에 있는 데이터만 추출
            VarChar2 타입 일 경우 0 아닐 경우 1 저장
            not null 일 경우 0 아닐 경우 1 저장

        */ 

        /* List 로 저장된 한 줄 한 줄 데이터를 컬럼명 , 데이터 타입 , Not Null Check 값 
            각각 다시 리스트로 저장
        */ 

        for(int i=0;i<count;i++){
           // String xml_data = String.format("<xs:element name='%s'\tform='%s'\ttype='%s'\t/>", name , form, type);
        }
        
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
