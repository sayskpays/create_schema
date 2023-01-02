package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class MakeVO {
    private static boolean env;
    private static boolean transmission;
    private static boolean fileExsist;
    private List<String> interfacefileList;
    private List<String> schemaFileList;
    private List<String> newInterfaceName;
    private List<String> newSchemaeName;

    public boolean isEnv() {
        return this.env;
    }

    public void setEnv(boolean env) {
        this.env = env;
    }

    public boolean isTransmission() {
        return this.transmission;
    }

    public void setTransmission(boolean transmission) {
        this.transmission = transmission;
    }

    public boolean isFileExsist() {
        return this.fileExsist;
    }

    public void setFileExsist(boolean fileExsist) {
        this.fileExsist = fileExsist;
    }

    public List<String> getInterfacefileList() {
        return this.interfacefileList;
    }

    public void setInterfacefileList(List<String> interfacefileList) {
        this.interfacefileList = interfacefileList;
    }

    public List<String> getSchemaFileList() {
        return this.schemaFileList;
    }

    public void setSchemaFileList(List<String> schemaFileList) {
        this.schemaFileList = schemaFileList;
    }

    public List<String> getNewInterfaceName() {
        return this.newInterfaceName;
    }

    public void setNewInterfaceName(List<String> newInterfaceName) {
        this.newInterfaceName = newInterfaceName;
    }

    public List<String> getNewSchemaeName() {
        return this.newSchemaeName;
    }

    public void setNewSchemaeName(List<String> newSchemaeName) {
        this.newSchemaeName = newSchemaeName;
    }

}
