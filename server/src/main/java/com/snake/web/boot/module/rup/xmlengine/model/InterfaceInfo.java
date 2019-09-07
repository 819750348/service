package com.snake.web.boot.module.rup.xmlengine.model;

import java.util.ArrayList;
import java.util.List;

public class InterfaceInfo {

    //接口名称
    private  String name ;
    //接口输入参数名称
    private List<String> inputParameterNames = new ArrayList<>();
    //输入参数值
    private List<List<String>> inputParameterValues = new ArrayList<>() ;
    //返回值描述
    private  String returnType ;
    //返回值类型
    private  String returnDesc ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getInputParameterNames() {
        return inputParameterNames;
    }

    /**
     * 增加参数名
     * @param arameterName
     */
    public void addInputParameterNames(String arameterName) {
        this.inputParameterNames.add(arameterName);
    }

    /**
     * 获取参数值列表
     * @return
     */
    public List<List<String>> getInputParameterValues() {
        return inputParameterValues;
    }

    /**
     * 增加参数值
     * @param inputParameterValues
     */
    public void addInputParameterValues(List<String> inputParameterValues) {
        this.inputParameterValues.add(inputParameterValues);
    }

    /**
     * 获取返回参数类型
     * @return
     */
    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * 获取返回类型描述
     * @return
     */
    public String getReturnDesc() {
        return returnDesc;
    }

    public void setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
    }
}
