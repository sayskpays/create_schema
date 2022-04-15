package com.example.demo.controller;

import java.util.ArrayList;
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
         * 생성시켜야 할 데이터 갯수(라인) count
         */
        int count = 1;
        char[] words = data.toCharArray();
        for (int i = 0; i < words.length; i++) {
            if (words[i] == '\n') {
                count++;
            }
        }
        /*
         * 들어오는 create query 데이터 가공 {\n} 분기별로 한 줄씩 데이터 저장
         */
        String[] dataColumn = data.split("\n");

        String form_varchar = "xs:string";
        String type_number = "xs:decimal";
        String type_nill = "nillable='true'";

        ArrayList<String> create_data = new ArrayList<>();

        for (int i = 0; i < dataColumn.length; i++) {
            // not null , string
            if (dataColumn[i].contains("NOT") && dataColumn[i].contains("VARCHAR")) {
                String xml_data = String.format("<xs:element name=''\tform='unqualified'\ttype='%s'\t/>"+"\r\n", form_varchar);
                create_data.add(xml_data);

                // not null , number
            } else if (dataColumn[i].contains("NOT") && dataColumn[i].contains("NUMBER")) {
                String xml_data = String.format("<xs:element name=''\tform='unqualified'\ttype='%s'\t/>"+"\r\n", type_number);
                create_data.add(xml_data);
                // nillable , string
            } else if (dataColumn[i].contains("VARCHAR")) {
                String xml_data = String.format("<xs:element name=''\tform='unqualified'\t %s \ttype='%s'\t/>" +"\r\n", type_nill, form_varchar);
                create_data.add(xml_data);
                // nillable , number
            } else if (dataColumn[i].contains("NUMBER")) {
                String xml_data = String.format("<xs:element name=''\tform='unqualified'\t %s \ttype='%s'\t/>" +"\r\n", type_nill, type_number);
                create_data.add(xml_data);
            }
            System.out.println("Create data get : " + create_data.get(i));
        }

        /* 
        create_data.get(i)의 1줄씩 나오는 문자열 값을 가지고 data format
        VARCHAR2 , NUMBER 이전에 한칸 공백 VAR , NUM 까지의 IndexOf를 찾고 공백까지 포함한 -3 SubString이 컬럼 명이다 0~ -3(Space,"문자까지 포함하니까)
        */

        if (data_type == "schema") {

            mv.addObject("xml_data", create_data);

        }

        mv.addObject("xml_data", create_data);
        mv.setViewName("hello");
        return mv;
    }
}
