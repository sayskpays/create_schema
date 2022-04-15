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

        // xml 생성 데이터 리스트
        ArrayList<String> create_data = new ArrayList<>();
        // index 리스트 저장 
        ArrayList<Integer> index_data = new ArrayList<>();
        // 데이터 컬럼 저장 리스트
        ArrayList<String> columnList = new ArrayList<>();

        String form_varchar = "xs:string";
        String type_number = "xs:decimal";
        String type_nill = "nillable='true'";

        // 폼에서 전달해주는 input data
        String data = request.getParameter("id");
        // 폼에서 전달해주는 data type
        String data_type = request.getParameter("data_type");
        int count = 1;
        char[] words = data.toCharArray();
        
        //들어오는 create query 데이터 가공 {\n} 분기별로 한 줄씩 데이터 저장
        String[] dataColumn = data.split("\n");
        
        /*
         * 생성시켜야 할 데이터 갯수(라인) count
         */
        for (int i = 0; i < words.length; i++) {
            if (words[i] == '\n') {
                count++;
            }
        }

        /*한줄씩 인덱스 값 구하기
        데이터 컬럼 값 리스트 뽑아내기
        */
        for(int i=0; i<dataColumn.length; i++){

            int indexData = dataColumn[i].indexOf("\" ");
            index_data.add(indexData);

            // 인덱스 숫자
            System.out.println(indexData);

            columnList.add(dataColumn[i].substring(1,indexData));

            // 컬럼 데이터 리스트
            System.out.println("columnList : " + columnList.get(i));
        }

        for (int i = 0; i < dataColumn.length; i++) {
            // not null , string
            if (dataColumn[i].contains("NOT") && dataColumn[i].contains("VARCHAR")) {
                String xml_data = String.format("<xs:element name='%s' form='unqualified' type='%s'/>\r\n",columnList.get(i) ,form_varchar);
                create_data.add(xml_data);

                // not null , number
            } else if (dataColumn[i].contains("NOT") && dataColumn[i].contains("NUMBER")) {
                String xml_data = String.format("<xs:element name='%s' form='unqualified' type='%s'/>\r\n",columnList.get(i) ,type_number);
                create_data.add(xml_data);
                // nillable , string
            } else if (dataColumn[i].contains("VARCHAR")) {
                String xml_data = String.format("<xs:element name='%s' form='unqualified' %s type='%s'/>\r\n",columnList.get(i) ,type_nill, form_varchar);
                create_data.add(xml_data);
                // nillable , number
            } else if (dataColumn[i].contains("NUMBER")) {
                String xml_data = String.format("<xs:element name='%s' form='unqualified' %s type='%s'/>\r\n", columnList.get(i),type_nill, type_number);
                create_data.add(xml_data);
            }
    
        }

        if (data_type == "schema") {

            mv.addObject("xml_data", create_data);

        }
        
        mv.addObject("xml_data", create_data);
        mv.addObject("column_data", columnList);
        mv.setViewName("hello");
        return mv;
    }
}
