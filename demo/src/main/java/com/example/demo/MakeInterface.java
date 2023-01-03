package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class MakeInterface {

    // filed
    private static boolean env = false; // UAT == true, PROD == false
    private static boolean transmission = false; // SND == true , RCV == false

    // method
    private static boolean envSet(String filePath) {

        if (filePath.contains("UAT")) {
            env = true;
        }

        return env;
    }

    private static boolean transmissionSet(String filePath) {

        if (filePath.contains("SND")) {
            transmission = true;
        }

        return transmission;
    }

    // 폴더에 있는 파일 리스트 탐색
    private static void scanDir(String folderPath, List<String> fileLst) {
        File[] files = new File(folderPath).listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                scanDir(f.getAbsolutePath(), fileLst);
            } else {
                fileLst.add(f.getAbsolutePath());
            }
        }
    }

    // origin 인터페이스 경로에 있는 프로세스 명만 추출
    private static String getOriginInterfaceName(String filePath) {
        String[] originInterfaceName = filePath.split("\\\\");
        String id = originInterfaceName[originInterfaceName.length - 1];

        return id;
    }

    // 인터페이스 폴더 경로 Check
    private static boolean isExistFile(List<String> fileList) {

        boolean fileEx = false;

        for (String file : fileList) {
            if (file.contains(".process")) {
                fileEx = true;
            }
        }
        return fileEx;
    }

    // 스키마 폴더 경로 Check
    private static boolean isExistSchema(List<String> schemaList) {

        boolean fileEx = false;

        for (String file : schemaList) {
            if (file.contains(".xsd")) {
                fileEx = true;
            }
        }
        return fileEx;
    }

    private static String copyFile(String inFilePath, String originFile, String outInterface, String outSchema) {

        // origin 인터페이스 파일 경로 설정
        StringBuilder inputSb = new StringBuilder(inFilePath);
        inputSb.append("\\");
        inputSb.append(originFile);

        File orgFile = new File(inputSb.toString());

        // new 인터페이스 파일 경로 설정
        StringBuilder sb = new StringBuilder(inFilePath);
        sb.append("\\");
        sb.append(outInterface);
        sb.append("_");
        sb.append(outSchema);
        sb.append(".process");

        File outFile = new File(sb.toString());

        System.out.println(sb);

        try {
            FileUtils.copyFile(orgFile, outFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return sb.toString();
    }

    private static String copySchema(String schemaStandard, String outSchema, String newSchemaName) {
        // origin 스키마 파일 경로 설정
        StringBuilder originSchema = new StringBuilder(schemaStandard);
        originSchema.append("\\");
        originSchema.append(outSchema);

        File originSchemaFile = new File(originSchema.toString());

        // new 스키마 파일 경로 설정
        StringBuilder newSchema = new StringBuilder(schemaStandard);
        newSchema.append("\\");
        newSchema.append(newSchemaName);
        newSchema.append(".xsd");

        File newSchemaFile = new File(newSchema.toString());
        System.out.println(originSchema);

        try {
            FileUtils.copyFile(originSchemaFile, newSchemaFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return newSchema.toString();
    }

    // 파일 내부 replace
    private static boolean replaceFile(String fileKind, String defaultInterface, String defaultSchema,
            String changeSchemaName) {

        // Path path = Paths.get(fileKind);
        Charset cs = StandardCharsets.UTF_8;
        File file = new File(fileKind);

        List<String> dataList = new ArrayList<>();
        String updateInterfaceName = null;
        String updateSchemaName = null;

        // 수정되는 인터페이스 명과 스키마명

        // 받아온게 인터페이스 명일 경우
        if (getOriginInterfaceName(fileKind).length() > 18) {
            updateInterfaceName = getOriginInterfaceName(fileKind).substring(0, 10);

            // 받아온게 스키마 명일 경우
        } else {
            updateSchemaName = getOriginInterfaceName(fileKind).substring(0, 12);
        }

        System.out.println("updateInterfaceName >>> " + updateInterfaceName);
        System.out.println("updateSchemaName >>> " + updateSchemaName);
        System.out.println("defaultInterface >>> " + defaultInterface);
        System.out.println("defaultSchema >>> " + defaultSchema);

        System.out.println("defaultInterface.substring(0, 10) >>> " + defaultInterface.substring(0, 10));
        System.out.println("defaultInterface substring >>> " + defaultInterface.substring(11, 23));
        System.out.println("changeSchemaName >>> " + changeSchemaName);

        // updateInterfaceName , updateSchemaName null 값에 따라서 인터페이스 수정이냐 스키마 수정이냐
        // 로직 분기

        try {
            // dataList = Files.readAllLines(path, cs);
            String data = FileUtils.readFileToString(file, "UTF-8");

            // 인터페이스 파일 수정 시
            if (updateInterfaceName != null) {
                data = data.replace(defaultInterface.substring(0, 10), updateInterfaceName);
                FileUtils.writeStringToFile(file, data, "UTF-8");
                data = data.replace(defaultInterface.substring(11, 23), changeSchemaName);
                FileUtils.writeStringToFile(file, data, "UTF-8");
                data = data.replace(defaultInterface.substring(11, 20).concat("TMI"), changeSchemaName.substring(0,9).concat("TMI"));
                FileUtils.writeStringToFile(file, data, "UTF-8");
                // 스키마 파일 수정 시
            } else if (updateSchemaName != null) {
                data = data.replace(defaultSchema.substring(0, 12), updateSchemaName);
                FileUtils.writeStringToFile(file, data, "UTF-8");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // for (String readline : dataList) {
        // System.out.println(readline);
        // }

        return false;

    }

    public static void main(String[] args) {
        List<String> interfacefileList = new ArrayList<>();
        List<String> schemaFileList = new ArrayList<>();
        List<String> newInterfaceName = new ArrayList<>();
        List<String> newSchemaeName = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        System.out.println("Input Origin Interface Path >>>");
        String fileStandard = in.nextLine();

        System.out.println("Input Origin Schema Path >>>");
        String schemaStandard = in.nextLine();

        System.out.println("[INTERFACE_NAME,SCHEMA_NAME] >>>");
        while (true) {
            String allData = in.nextLine();

            if (allData.equals("q")) {
                break;
            } else {
                String[] test = allData.split(",");
                newInterfaceName.add(test[0]);
                newSchemaeName.add(test[1]);
            }
        }

        ///////////// 인터페이스 복사 관련 메소드 ////////////////

        // 입력한 경로의 파일 리스트 탐색
        scanDir(fileStandard, interfacefileList);
        // 파일 리스트 안의 무작위 파일 하나 인터페이스명 (copy시 이용)
        String defalutInterface = getOriginInterfaceName(interfacefileList.get(2));
        // 새로 개발할 인터페이스 아이디 중복 체크

        /////// 스키마 관련 복사 메소드 //////////////
        scanDir(schemaStandard, schemaFileList);
        String defalutSchema = getOriginInterfaceName(schemaFileList.get(2));

        // 파일 탐색 후 똑같은 인터페이스가 있지 않은 경우 파일 복사
        if (isExistFile(interfacefileList) && isExistSchema(schemaFileList)) {
            for (int i = 0; i < newInterfaceName.size(); i++) {
                String cf = copyFile(fileStandard, defalutInterface, newInterfaceName.get(i), newSchemaeName.get(i));
                String cs = copySchema(schemaStandard, defalutSchema, newSchemaeName.get(i));

                // 파일 replace 로직
                replaceFile(cf, defalutInterface, defalutSchema, newSchemaeName.get(i));
                replaceFile(cs, defalutInterface, defalutSchema, newSchemaeName.get(i));

            }
        } else {
            System.out.println("인터페이스 또는 스키마 경로가 잘못 되었습니다.");
        }

    }
}
