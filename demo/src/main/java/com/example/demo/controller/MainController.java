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

        // (스키마) xml 생성 데이터 리스트
        ArrayList<String> create_data = new ArrayList<>();
        // index 리스트 저장 
        ArrayList<Integer> index_data = new ArrayList<>();
        // 데이터 컬럼 저장 리스트
        ArrayList<String> columnList = new ArrayList<>();
        // Select 스키마 저장 리스트
        ArrayList<String[]> selectList = new ArrayList<>();
        // Record 스키마 저장 리스트
        ArrayList<String> recordList = new ArrayList<>();
        // (수신) parameter 저장 리스트
        ArrayList<String> parameterList = new ArrayList<>();

        String form_varchar = "xs:string";
        String type_number = "xs:decimal";
        String type_nill = "nillable='true'";
        String parameter = "parameter";
        // 폼에서 전달해주는 input data
        String data = request.getParameter("id");
        String question = "?,";
        
    
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

        /*
         * 컬럼 갯수에 따른 ? 구하기 (수신 insert 적용)
         * 
         */

        String sb = question.repeat(count);
        sb = sb.substring(0, sb.length()-1);
        
        /*한줄씩 인덱스 값 구하기
        데이터 컬럼 값 리스트 뽑아내기
        */
        for(int i=0; i<dataColumn.length; i++){

            int indexData = dataColumn[i].indexOf("\" ");
            index_data.add(indexData);
            columnList.add(dataColumn[i].substring(1,indexData));

        }

        for (int i = 0; i < dataColumn.length; i++) {
            // not null , string
            if (dataColumn[i].contains("NOT NULL ENABLE") && dataColumn[i].contains("VARCHAR")) {
                // XML schema
                String xml_data = String.format("<xs:element name='%s' form='unqualified' type='%s'/>\r\n",columnList.get(i) ,form_varchar);
                // Queryoutput
                String select_schema01 = String.format("<QueryOutputCachedSchemaColumns>%s</QueryOutputCachedSchemaColumns>", columnList.get(i)).trim()+"\r\n";
                String select_schema02 = String.format("<QueryOutputCachedSchemaDataTypes>12</QueryOutputCachedSchemaDataTypes>").trim()+"\r\n";
                String select_schema03 = String.format("<QueryOutputCachedSchemaDataTypesName>VARCHAR2</QueryOutputCachedSchemaDataTypesName>").trim()+"\r\n";
                String select_schema04 = String.format("<QueryOutputCachedSchemaStatus>RequiredElement</QueryOutputCachedSchemaStatus>").trim()+"\r\n";
                // xml schema
                String record_data = String.format("<%s>\r\n<xsl:value-of select=\"%s\"/>\r\n</%s>\r\n",columnList.get(i),columnList.get(i),columnList.get(i));
                // (수신) parameter schema
                String parameter_data = String.format("<%s>\r\n<%sName>%s</%sName>\r\n<dataType>VARCHAR</dataType>\r\n</%s>\r\n",parameter,parameter,columnList.get(i),parameter,parameter);

                String[] select_schema = {select_schema01,select_schema02,select_schema03,select_schema04};
                selectList.add(select_schema);
                create_data.add(xml_data);
                recordList.add(record_data);
                parameterList.add(parameter_data);

                // not null , number
            } else if (dataColumn[i].contains("NOT NULL ENABLE") && dataColumn[i].contains("NUMBER")) {
                // XML schema
                String xml_data = String.format("<xs:element name='%s' form='unqualified' type='%s'/>\r\n",columnList.get(i) ,type_number);
                //QueryOutput
                String select_schema01 = String.format("<QueryOutputCachedSchemaColumns>%s</QueryOutputCachedSchemaColumns>", columnList.get(i)).trim()+"\r\n";
                String select_schema02 = String.format("<QueryOutputCachedSchemaDataTypes>12</QueryOutputCachedSchemaDataTypes>").trim()+"\r\n";
                String select_schema03 = String.format("<QueryOutputCachedSchemaDataTypesName>NUMBER</QueryOutputCachedSchemaDataTypesName>").trim()+"\r\n";
                String select_schema04 = String.format("<QueryOutputCachedSchemaStatus>RequiredElement</QueryOutputCachedSchemaStatus>").trim()+"\r\n";
                //xsl schema
                String record_data = String.format("<%s>\r\n<xsl:value-of select=\"%s\"/>\r\n</%s>\r\n",columnList.get(i),columnList.get(i),columnList.get(i));
                //(수신) parameter schema
                String parameter_data = String.format("<%s>\r\n<%sName>%s</%sName>\r\n<dataType>NUMERIC</dataType>\r\n</%s>\r\n",parameter,parameter,columnList.get(i),parameter,parameter);
                String[] select_schema = {select_schema01,select_schema02,select_schema03,select_schema04};

                selectList.add(select_schema);     
                recordList.add(record_data);
                create_data.add(xml_data);
                parameterList.add(parameter_data);

                // nillable , string
            } else if (!(dataColumn[i].contains("NOT NULL ENABLE")) && dataColumn[i].contains("VARCHAR")) {
                // XML Schema
                String xml_data = String.format("<xs:element name='%s' form='unqualified' %s type='%s'/>\r\n",columnList.get(i) ,type_nill, form_varchar);

                //QueryOutput
                String select_schema01 = String.format("<QueryOutputCachedSchemaColumns>%s</QueryOutputCachedSchemaColumns>", columnList.get(i)).trim()+"\r\n";
                String select_schema02 = String.format("<QueryOutputCachedSchemaDataTypes>12</QueryOutputCachedSchemaDataTypes>").trim()+"\r\n";
                String select_schema03 = String.format("<QueryOutputCachedSchemaDataTypesName>VARCHAR2</QueryOutputCachedSchemaDataTypesName>").trim()+"\r\n";
                String select_schema04 = String.format("<QueryOutputCachedSchemaStatus>NillableElement</QueryOutputCachedSchemaStatus>").trim()+"\r\n";

                //xsl schema
                String record_data = String.format("<%s>\r\n<xsl:copy-of select=\"%s/@xsi:nil\"/>\r\n<xsl:value-of select=\"%s\"/>\r\n</%s>\r\n",columnList.get(i),columnList.get(i),columnList.get(i),columnList.get(i));

                // 수신 parameter schema
                String parameter_data = String.format("<%s>\r\n<%sName>%s</%sName>\r\n<dataType>VARCHAR</dataType>\r\n</%s>\r\n",parameter,parameter,columnList.get(i),parameter,parameter);
                String[] select_schema = {select_schema01,select_schema02,select_schema03,select_schema04};
                selectList.add(select_schema);    
                recordList.add(record_data);
                create_data.add(xml_data);
                parameterList.add(parameter_data);
                // nillable , number
            } else if (!(dataColumn[i].contains("NOT NULL ENABLE")) && dataColumn[i].contains("NUMBER")) {

                // xml Schema
                String xml_data = String.format("<xs:element name='%s' form='unqualified' %s type='%s'/>\r\n", columnList.get(i),type_nill, type_number);

                //QueryOutput
                String select_schema01 = String.format("<QueryOutputCachedSchemaColumns>%s</QueryOutputCachedSchemaColumns>", columnList.get(i)).trim()+"\r\n";
                String select_schema02 = String.format("<QueryOutputCachedSchemaDataTypes>12</QueryOutputCachedSchemaDataTypes>").trim()+"\r\n";
                String select_schema03 = String.format("<QueryOutputCachedSchemaDataTypesName>NUMBER</QueryOutputCachedSchemaDataTypesName>").trim()+"\r\n";
                String select_schema04 = String.format("<QueryOutputCachedSchemaStatus>NillableElement</QueryOutputCachedSchemaStatus>").trim()+"\r\n";

                // xsl schema
                String record_data = String.format("<%s>\r\n<xsl:copy-of select=\"%s/@xsi:nil\"/>\r\n<xsl:value-of select=\"%s\"/>\r\n</%s>\r\n",columnList.get(i),columnList.get(i),columnList.get(i),columnList.get(i)); 

                // 수신 parameter schema
                String parameter_data = String.format("<%s>\r\n<%sName>%s</%sName>\r\n<dataType>NUMERIC</dataType>\r\n</%s>\r\n",parameter,parameter,columnList.get(i),parameter,parameter);

                String[] select_schema = {select_schema01,select_schema02,select_schema03,select_schema04};

                selectList.add(select_schema);    
                recordList.add(record_data);
                create_data.add(xml_data);
                parameterList.add(parameter_data);
            }
        }

        mv.addObject("recordList",recordList);
        mv.addObject("selectList", selectList);
        mv.addObject("xml_data", create_data);
        mv.addObject("column_data", columnList);
        mv.addObject("parameterList", parameterList);
        mv.addObject("question", sb);
        mv.addObject("count", count);
        mv.setViewName("hello");
        return mv;
    }

    @PostMapping("/error") 
    public ModelAndView error(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("error");

        return mv;
    }
}
