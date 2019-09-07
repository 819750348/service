package com.snake.web.boot.module.rup.xmlengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelViewInfo {

    //
    public static  final  int LABEL = 0;
    public static  final  int TABLE = 1;
    //属性结构
    public static  final  int TREE = 2;
    //checkbox结构
    public static  final  int CHECKBOX = 3;
    //函数结构
    public static  final  int FUNCTION = 4;
    //结构体
    public static  final  int STRUCTURE = 5;
    //接口
    public  static  final  int INTERFACE = 6;


    //字段名
    private  String name ;
    //字段类型
    private  int type ;
    //字段值
    private  String value ;
    //列名
    private List<String> columnNames=new ArrayList<>();
    //列值
    private  List<List<String>> columnValues = new ArrayList<>();
    //其他属性
    private Map<String,String> attribute;
    //函数列表
    private  List<FunctionInfo> functionInfos =new ArrayList<>();
    //结构体列表
    private  List<StructureInfo> structureInfos = new ArrayList<>();
    //值的单位
    private  String valueUnit ;

    //是否起用
    private  boolean valueEnable=false;


    //接口信息
    private  InterfaceInfo interfaceInfo ;
    public ModelViewInfo(){
        attribute = new HashMap<String,String>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void addColumnNames(String name) {
        this.columnNames.add(name);
    }

    public List<List<String>> getColumnValues() {
        return columnValues;
    }

    public void addColumnValues(List<String> columnValues) {
        this.columnValues.add(columnValues);;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }

    public void addAttribute(String name,String value) {
        attribute.put(name,value);
    }

    public List<FunctionInfo> getFunctionInfos() {
        return functionInfos;
    }

    public void addFunctionInfos(FunctionInfo functionInfo) {
        this.functionInfos.add(functionInfo);
    }

    public List<StructureInfo> getStructureInfos() {
        return structureInfos;
    }

    /**
     * 添加结构体
     * @param structureInfos
     */
    public void addStructureInfos(StructureInfo structureInfos) {
        this.structureInfos.add(structureInfos);
    }


    public InterfaceInfo getInterfaceInfo() {
        return interfaceInfo;
    }

    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
    }

    public String getValueUnit() {
        return valueUnit;
    }

    public void setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
    }

    public boolean isValueEnable() {
        return valueEnable;
    }

    public void setValueEnable(boolean valueEnable) {
        this.valueEnable = valueEnable;
    }
}
