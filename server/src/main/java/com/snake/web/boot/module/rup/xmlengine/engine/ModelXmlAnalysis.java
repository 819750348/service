package com.snake.web.boot.module.rup.xmlengine.engine;

import com.snake.web.boot.module.rup.model.ModelInfo;
import com.snake.web.boot.module.rup.util.Dom4jUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class ModelXmlAnalysis {

    public static final String VALUE = "value";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String LABEL = "label";
    public static  final String ISARRAY = "isArray" ;

    public static  final  String COLUMNS = "columns" ;
    private static int basic_value = 0;//直接是value
    private static int basic_object = 1;//直接是对象
    private static int basic_table = 2; //直接是JSONAarry
    private static int complex = 3;//复杂类型，包含简单类型
//    private static int rootNode = 4;//根节点信息
//    private static int rootNodes = 5;//根节点，子节点是列表.
    /**
     * 获取模型信息
     */
    private ModelInfo modelInfo;
    private Dom4jUtil dom4jUtil;
    private List<JSONObject> viewInfoList;
    //文件路径
    private String filePath;
    public ModelXmlAnalysis(String filePath) {
        this.filePath = filePath;
        modelInfo = new ModelInfo();
        dom4jUtil = new Dom4jUtil(filePath);
        viewInfoList = new ArrayList<>();
        build();
    }

    /**
     * 获取基本信息
     *
     * @return
     */
    public ModelInfo getModelInfo() {
        return this.modelInfo;
    }


    /**
     * 获取展示信息
     *
     * @return
     */
    public List<JSONObject> getModelView() {
        return this.viewInfoList;
    }


    private void build() {
        Element root = dom4jUtil.getDocument().getRootElement();
        // 枚举所有子节点
        int count = 0;
        for (Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element e = (Element) i.next();
            String text = e.getText();
            if (count == 0) {//进行基本信息分析
                analysisModelInfo();
            } else {
                JSONObject obj = new JSONObject();
                int type = 0;
                obj.put(NAME, e.getQName().getName());
                //判断是否有子
                if(!haveChild(e)){//没有子直接进行处理
                     type = getType(e);
                    if (type == basic_value) {//这种类型忽略掉
                        obj.put(VALUE, analysisBaicValue(e));
                    } else if (type == basic_object) {
                        obj.put(VALUE, analysisBaicObject(e));
                    }
                    obj.put(ISARRAY, false);
                }else{//有子元素
                    obj.put(ISARRAY, true);
                    JSONArray jsonArray = new JSONArray();
                    for (Iterator it = e.elementIterator(); it.hasNext(); ) {//遍历子节点
                        Element element = (Element) it.next();
                        type = getType(element);
                        if (type == basic_value) {//这种类型忽略掉
                            jsonArray.add(analysisBaicValue(element));
                        } else if (type == basic_object) {
                            jsonArray.add( analysisBaicObject(element));
                        } else if (type == basic_table) {
                            jsonArray.add( analysisBasicTable(element));
                        } else if (type == complex) {
                            jsonArray.add(analysisComplex(element));
                        }
                    }
                    obj.put(VALUE, jsonArray);
                }
                viewInfoList.add(obj);
            }
            count++;
            System.out.println(text);
        }
    }


    private void analysisModelInfo() {
        //获取基本信息
        List list = dom4jUtil.getDocument().selectNodes("/Root/模型基本信息");
        if (list.size() > 0) {
            Element e = (Element) list.get(0);
            //枚举所有子节点
            for (Iterator it = e.elementIterator(); it.hasNext(); ) {
                Element element = (Element) it.next();
                String eName = element.getQName().getName();
                String text = element.getText();
                if ("模型名称".equals(eName)) {
                    this.modelInfo.setModelName(text);
                } else if ("模型编写时间".equals(eName)) {
                    this.modelInfo.setWriteTime(text);
                } else if ("模型编写人".equals(eName)) {
                    this.modelInfo.setDeveloper(text);
                } else if ("模型编写单位".equals(eName)) {
                    this.modelInfo.setModelDepartment(text);
                } else if ("模型描述信息".equals(eName)) {
                    this.modelInfo.setDescription(text);
                } else if ("模型标签".equals(eName)) {
                    this.modelInfo.setModelLabel(text);
                }
            }
        }
    }



    /**
     * 分析基本类型
     *
     * @param e
     * @return
     */
    private JSONObject analysisBaicValue(Element e) {
        JSONObject object = new JSONObject();
        object.put(TYPE, basic_value);
        object.put(VALUE, e.getText());
        object.put(NAME, e.getQName().getName());
        return object;
    }


    /**
     * 基本对象类型
     *
     * @param e
     * @return
     */
    private JSONObject analysisBaicObject(Element e) {
        JSONObject object = new JSONObject();
        object.put(TYPE, basic_object);
        object.put(NAME, e.getQName().getName());
        JSONObject pObject = new JSONObject();
        for (Iterator j = e.attributeIterator(); j.hasNext(); ) {//获取属性
            Attribute attribute = (Attribute) j.next();
            pObject.put(attribute.getQName().getName(), attribute.getText());//设置属性名和属性值
        }
        object.put(VALUE, pObject);
        return object;
    }


    /**
     * 分析表格类型
     *
     * @param e
     * @return
     */
    private JSONObject analysisBasicTable(Element e) {
        JSONObject object = new JSONObject();
        object.put(TYPE, basic_table);
        object.put(NAME, e.getQName().getName());
        JSONArray jsonArray = new JSONArray();
        JSONArray columnArray = new JSONArray();
        boolean isAddColunm = true ;
        for (Iterator it = e.elementIterator(); it.hasNext(); ) {//遍历子节点
            Element element = (Element) it.next();
            object.put(LABEL,element.getQName().getName());//设置标签
            JSONObject pObject = new JSONObject();
            for (Iterator j = element.attributeIterator(); j.hasNext(); ) {//获取属性
                Attribute attribute = (Attribute) j.next();
                pObject.put(attribute.getQName().getName(), attribute.getText());//设置属性名和属性值
                if(isAddColunm){
                    columnArray.add(attribute.getQName().getName());
                }
            }
            jsonArray.add(pObject);
            isAddColunm=false ;//只加入一次
        }
        object.put(VALUE, jsonArray);
        object.put(COLUMNS,columnArray);//添加列名
        return object;
    }

    /**
     * 分析复杂类型
     *
     * @param e
     * @return
     */
    private JSONObject analysisComplex(Element e) {
        JSONObject object = new JSONObject();
        object.put(TYPE, complex);
        object.put(NAME, e.getQName().getName());
        JSONArray jsonArray = new JSONArray();
        for (Iterator it = e.elementIterator(); it.hasNext(); ) {//遍历子节点
            Element element = (Element) it.next();
            int type = getType(element);
            if (type == basic_value) {
                jsonArray.add(analysisBaicValue(element));
            } else if (type == basic_object) {
                jsonArray.add(analysisBaicObject(element));
            } else if (type == basic_table) {
                jsonArray.add(analysisBasicTable(element));
            }
        }
        object.put(VALUE, jsonArray);
        return object;
    }

    /**
     * 获取数据类型
     *
     * @param e
     * @return
     */
    private int getType(Element e) {
        if (!haveChild(e)) {
            if (!StringUtils.isEmpty(e.getText())) {
                return basic_value; //基本值类型
            }
            return basic_object; //节本对象类型
        }
        //遍历子元素,判断子元素名称是否相同，如果相同，则为表格，如果不同，则为复杂数据类型
        String eName = "";
        boolean table = true;
        for (Iterator it = e.elementIterator(); it.hasNext(); ) {
            Element element = (Element) it.next();
            if (!StringUtils.isEmpty(eName) && !eName.equals(element.getQName().getName())) {
                table = false;
                break;
            }
            eName = element.getQName().getName();
        }
        if (table) {
            return basic_table;//返回表格
        }
        return complex;//返回复杂数据类型
    }


    /**
     * 判断是否含有子节点
     *
     * @param e
     * @return
     */
    private boolean haveChild(Element e) {
        for (Iterator it = e.elementIterator(); it.hasNext(); ) {
            return  true ;
        }
        return false;
    }

    /**
     * 保存xml
     * @param inf
     * @param vl
     * @param filePath
     * @return  0 失败，1 成功
     */
    public static int  saveXml(ModelInfo inf,List<JSONObject> vl,String filePath){
        int r =  createXMLFile(filePath,inf,vl);
        return  r ;
    }

    /**
     * 创建模型基本信息
     * @param inf
     * @param baiscInfoElement
     */
    private  static void  createBaiscInfoXml(ModelInfo inf,Element baiscInfoElement){
        createElement("模型名称",inf.getModelName(),baiscInfoElement);
        createElement("模型编写时间",inf.getWriteTime(),baiscInfoElement);
        createElement("模型编写人",inf.getDeveloper(),baiscInfoElement);
        createElement("模型编写单位",inf.getModelDepartment(),baiscInfoElement);
        createElement("模型描述信息",inf.getDescription(),baiscInfoElement);
        createElement("模型标签",inf.getModelLabel(),baiscInfoElement);
    }

    /**
     * 创建节点
     * @param name
     * @param value
     * @param e
     */
    private  static void  createElement(String  name,String value,Element e){
        Element element = e.addElement(name);//增加节点
        element.setText(value);//增加节点值
    }

    /**
     * 遍历JSONObject 属性进行赋值
     * @param e
     * @param jsonObject
     */
    private static void createAttribute(Element e,JSONObject jsonObject){
        Iterator<String> it = jsonObject.keys();
        while(it.hasNext()){
            // 获得key
            String key = it.next();
            String value = jsonObject.getString(key);
            e.addAttribute(key,value);
        }
    }

    /**
     * 创建table
     * @param e
     * @param jsonObject
     */
    private static  void createTable(Element e,JSONObject jsonObject){
        JSONArray jArray = jsonObject.getJSONArray(VALUE);
        for (Object o:jArray
             ) {
            JSONObject jo =(JSONObject) o;
            Element el = e.addElement(jsonObject.getString(LABEL));//创建标签
            createAttribute(el,jo);//创建属性
        }
    }

    /**
     * 建立一个XML文档,文档名由输入属性决定
     *
     * @param filename 需建立的文件名
     * @return 返回操作结果, 0表失败, 1表成功
     */
    private static int createXMLFile(String filename,ModelInfo inf,List<JSONObject> vl) {
        /** 返回操作结果, 0表失败, 1表成功 */
        int returnValue = 0;
        /** 建立document对象 */
        Document document = DocumentHelper.createDocument();
        /** 建立XML文档的根Root */
        Element root = document.addElement("Root");
        /** 加入第一个节点基本信息 */
        Element baiscInfoElement = root.addElement("模型基本信息");
        createBaiscInfoXml(inf,baiscInfoElement);
        //基本信息设置完成
        for(JSONObject jsonObject:vl){//遍历一级节点
            Element element1 = root.addElement(jsonObject.getString(NAME));
            boolean isArray = jsonObject.getBoolean(ISARRAY);
            if(!isArray){//单节点类型
                JSONObject e1JSONObject = jsonObject.getJSONObject(VALUE);
                int type = e1JSONObject.getInt(TYPE);
                if(type==basic_value){
                    element1.setText(e1JSONObject.getString(VALUE));
                }else if(type==basic_object){
                    createAttribute(element1,e1JSONObject.getJSONObject(VALUE));
                }

            }else{//列表形式
                JSONArray array = jsonObject.getJSONArray(VALUE);
                for (Object o :array) {//遍历二级节点
                    JSONObject jo = (JSONObject) o;//强制转化为JSONObject
                    int type = jo.getInt(TYPE);
                    if(type==basic_table){
                        createTable(element1, jo);//创建表格
                    }else if(type==complex){
                        JSONArray jsonArray = jo.getJSONArray(VALUE);//
                        for (Object j:jsonArray) {
                            JSONObject j2 = (JSONObject)j;//强制转化为
                            type = j2.getInt(TYPE);
                            Element element2 = element1.addElement(j2.getString(NAME));
                            if(type==basic_value){
                                element2.setText(j2.getString(VALUE));
                            }else if(type==basic_object){
                                createAttribute(element2,j2.getJSONObject(VALUE));
                            }else if(type==basic_table){
                                createTable(element2, j2);//创建表格
                            }
                        }
                    }
                }
            }
        }

//
//        /** 加入show属性内容 */
//        bookElement.addAttribute("show", "yes");
//        /** 加入title节点 */
//        Element titleElement = bookElement.addElement("title");
//        /** 为title设置内容 */
//        titleElement.setText("Dom4j Tutorials");
//        /** 类似的完成后两个book */
//        bookElement = booksElement.addElement("book");
//        bookElement.addAttribute("show", "yes");
//        titleElement = bookElement.addElement("title");
//        titleElement.setText("Lucene Studing");
//        bookElement = booksElement.addElement("book");
//        bookElement.addAttribute("show", "no");
//        titleElement = bookElement.addElement("title");
//        titleElement.setText("Lucene in Action");
//        /** 加入owner节点 */
//        Element ownerElement = booksElement.addElement("owner");
//        ownerElement.setText("O'Reilly");
        try {
            /** 将document中的内容写入文件中 */
            XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)));
            writer.write(document);
            writer.close();
            /** 执行成功,需返回1 */
            returnValue = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }

    public static void main(String[] args) {
        ModelXmlAnalysis modelXmlAnalysis = new ModelXmlAnalysis("E:\\项目管理\\二院项目\\项目三\\03开发\\模型描述文件模板20181212\\作战模型描述文件模板.xml");
        ModelInfo inf = modelXmlAnalysis.getModelInfo();
        List<JSONObject> vl = modelXmlAnalysis.getModelView();
        System.out.printf("解析完成");
        modelXmlAnalysis.saveXml(inf,vl,"d://restfulController.xml");
    }
}
