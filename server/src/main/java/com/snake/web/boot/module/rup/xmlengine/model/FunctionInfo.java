package com.snake.web.boot.module.rup.xmlengine.model;

import java.util.ArrayList;
import java.util.List;

public class FunctionInfo {
    //函数名
    private  String name ;
    //输入参数名称
    private List<String> inputParameterNames = new ArrayList<>();
    //输入参数值
    private List<List<String>> inputParameterValues = new ArrayList<>() ;
    //返回值描述
    private  String returnType ;
    //返回值类型
    private  String returnDesc ;
    //函数功能描述
    private  String functionDesc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInputParameterNames() {
        return inputParameterNames;
    }

    public void addInputParameterNames(String parameterName) {
        this.inputParameterNames.add(parameterName);
    }

    public List<List<String>> getInputParameterValues() {
        return inputParameterValues;
    }

    public void addInputParameterValues(List<String> inputParameterValues) {
        this.inputParameterValues.add(inputParameterValues);
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }
}
