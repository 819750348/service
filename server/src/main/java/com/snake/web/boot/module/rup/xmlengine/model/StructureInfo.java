package com.snake.web.boot.module.rup.xmlengine.model;

import java.util.ArrayList;
import java.util.List;

public class StructureInfo {

    //结构体名称
    private  String name ;
    //列名
    private List<String> columnNames=new ArrayList<>();
    //列值
    private  List<List<String>> columnValues = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
