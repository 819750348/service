//package com.snake.web.boot.module.rup.xmlengine.engine;
//
//import com.snake.web.boot.module.rup.model.ModelInfo;
//import com.snake.web.boot.module.rup.xmlengine.model.FunctionInfo;
//import com.snake.web.boot.module.rup.xmlengine.model.InterfaceInfo;
//import com.snake.web.boot.module.rup.xmlengine.model.ModelViewInfo;
//import com.snake.web.boot.module.rup.xmlengine.model.StructureInfo;
//import com.snake.web.boot.module.rup.util.Dom4jUtil;
//import org.dom4j.Attribute;
//import org.dom4j.Element;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class ModelXmlEngine {
//
//    //模型类型【1:装备;2:作战;3:环境;4:评估;5:支撑类】
//    public static final int EQUIPMENT = 1;
//    public static final int COMBAT = 2;
//    public static final int ENVIRONMENT = 3;
//    public static final int ASSESS = 4;
//    public static final int SUPPORT = 5;
//
//    private int type;
//    private String filePath;
//    private ModelInfo modelInfo;
//    private List<ModelViewInfo> modelViewInfoList;
//    private Dom4jUtil dom4jUtil;
//
//    public ModelXmlEngine(String filePath, int type) {
//        this.type = type;
//        this.filePath = filePath;
//        this.modelInfo = new ModelInfo();
//        this.modelViewInfoList = new ArrayList<ModelViewInfo>();
//        dom4jUtil = new Dom4jUtil(filePath);
//        build();
//    }
//
//    private void build() {
//        switch (type) {
//            case EQUIPMENT:
//                buildEquipment();
//                break;
//            case COMBAT:
//                buildCombat();
//                break;
//            case ENVIRONMENT:
//                buildEnvironment();
//                break;
//            case ASSESS:
//                buildAssess();
//                break;
//            case SUPPORT:
//                break;
//            default:
//                break;
//        }
//    }
//
//
//    /**
//     * 评估方法模型
//     */
//    private  void buildAssess(){
//        //1、第一步组装模型基本信息
//        analysisModelInfo();
//        //2、评估方法输入参数
//        analysisTable("/Root/评估方法输入参数");
//        //3、评估方法输出参数
//        analysisTable("/Root/评估方法输出参数");
//        //4、复杂数据结构
//        analysisStructure("/Root/复杂数据结构");
//    }
//
//
//    /**
//     * 环境模型
//     */
//    private  void buildEnvironment(){
//        //1、第一步组装模型基本信息
//        analysisModelInfo();
//        //2、模型初始化参数
//        analysisTable("/Root/模型初始化参数");
//        //3、模型输入参数
//        analysisTable("/Root/模型输入参数");
//        //4、模型输出参数
//        analysisTable("/Root/模型输出参数");
//        //5、模型功能函数
//        analysisFunction("/Root/模型功能函数");
//        //6、模型初始化接口
//        analysisInterface("/Root/模型初始化接口");
//        //7、复杂数据结构
//        analysisStructure("/Root/复杂数据结构");
//    }
//
//
//    /**
//     * 作战模型
//     */
//    private  void buildCombat(){
//        //1、第一步组装模型基本信息
//        analysisModelInfo();
//        //2、模型调用帧周期
//        analysisT();
//        //3、模型参数
//        analysisTable("/Root/模型参数");
//        //4、模型功能函数
//        analysisFunction("/Root/模型功能函数");
//        //5、模型输入接口
//        analysisFunction("/Root/模型输入接口");
//        //6、模型输出接口
//        analysisTable("/Root/模型输出接口");
//        //7、模型初始化接口
//        analysisTable("/Root/模型初始化接口");
//        //8、模型控制接口
//        analysisTable("/Root/模型控制接口");
//        //9、复杂数据结构
//        analysisStructure("/Root/复杂数据结构");
//    }
//
//
//
//    /**
//     *装备模型
//     */
//    private void buildEquipment() {
//        //1、第一步组装模型基本信息
//        analysisModelInfo();
//        //2、模型调用帧周期
//        analysisT();
//        //3、模型想定参数
//        analysisTable("/Root/模型想定参数");
//        //4、模型性能参数
//        analysisTable("/Root/模型性能参数");
//        //5、模型功能函数
//        analysisFunction("/Root/模型功能函数");
//        //6、模型输入接口
//        analysisTable("/Root/模型输入接口");
//        //7、模型输出接口
//        analysisTable("/Root/模型输出接口");
//        //8、模型初始化接口
//        analysisTable("/Root/模型初始化接口");
//        //9、模型控制接口
//        analysisTable("/Root/模型控制接口");
//        //10、复杂数据结构
//        analysisStructure("/Root/复杂数据结构");
//
//    }
//
//
//    /**
//     * 解析结构体模型
//     * @param xpsth
//     */
//    private  void analysisStructure(String xpsth){
//        List list = dom4jUtil.getDocument().selectNodes(xpsth);
//        if (list.size() > 0) {
//            Element e = (Element) list.get(0);
//            ModelViewInfo info = new ModelViewInfo();
//            info.setType(ModelViewInfo.STRUCTURE);
//            info.setName(e.getQName().getName());
//            //遍历子元素,结构体名称
//            for (Iterator it = e.elementIterator(); it.hasNext(); ) {
//                Element element = (Element) it.next();
//                StructureInfo structureInfo = new StructureInfo();
//                for (Iterator j = element.attributeIterator(); j.hasNext(); ) {//设置结构体名称
//                    Attribute attribute = (Attribute) j.next();
//                    structureInfo.setName(attribute.getText());
//                }
//
//                int lineNum = 0;
//                //函数的详细信息
//                for (Iterator itdetail = element.elementIterator(); itdetail.hasNext(); ) {
//                    Element detailElement = (Element) itdetail.next();
//                    if ("数据项".equals(detailElement.getQName().getName())) {
//                        List<String> dataValues = new ArrayList<>();
//                        for (Iterator j = detailElement.attributeIterator(); j.hasNext(); ) {//设置函数名
//                            Attribute attribute = (Attribute) j.next();
//                            if (lineNum == 0) {
//                                structureInfo.addColumnNames(attribute.getName());
//                            }
//                            dataValues.add(attribute.getText());
//                        }
//                        structureInfo.addColumnValues(dataValues);
//                        lineNum++;
//                    }
//                }
//                info.addStructureInfos(structureInfo);
//            }
//            this.modelViewInfoList.add(info);
//        }
//    }
//
//    /**
//     * 解析接口类型
//     * @param xpsth
//     */
//    private  void analysisInterface(String xpsth){
//        List list = dom4jUtil.getDocument().selectNodes(xpsth);
//        if (list.size() > 0) {
//            Element e = (Element) list.get(0);
//            ModelViewInfo info = new ModelViewInfo();
//            info.setType(ModelViewInfo.INTERFACE);
//            info.setName(e.getQName().getName());
//            //遍历子元素,参数及返回值
//            int lineNum = 0;
//            InterfaceInfo interfaceInfo = new InterfaceInfo();
//            for (Iterator j = e.attributeIterator(); j.hasNext(); ) {//设置接口名称
//                Attribute attribute = (Attribute) j.next();
//                interfaceInfo.setName(attribute.getText());
//            }
//            //接口的详细信息
//            for (Iterator itdetail = e.elementIterator(); itdetail.hasNext(); ) {
//                Element detailElement = (Element) itdetail.next();
//                if ("输入参数".equals(detailElement.getQName().getName())) {
//                    List<String> inputValues = new ArrayList<>();
//                    for (Iterator j = detailElement.attributeIterator(); j.hasNext(); ) {//设置函数名
//                        Attribute attribute = (Attribute) j.next();
//                        if (lineNum == 0) {
//                            interfaceInfo.addInputParameterNames(attribute.getName());
//                        }
//                        inputValues.add(attribute.getText());
//                    }
//                    interfaceInfo.addInputParameterValues(inputValues);
//                    lineNum++;
//                } else if ("返回值".equals(detailElement.getQName().getName())) {
//                    for (Iterator j = detailElement.attributeIterator(); j.hasNext(); ) {//设置函数名
//                        Attribute attribute = (Attribute) j.next();
//                        if ("返回值类型".equals(attribute.getName())) {
//                            interfaceInfo.setReturnType(attribute.getText());
//                        } else if ("返回值描述".equals(attribute.getName())) {
//                            interfaceInfo.setReturnDesc(attribute.getText());
//                        }
//                    }
//                }
//            }
//            info.setInterfaceInfo(interfaceInfo);
//            this.modelViewInfoList.add(info);
//        }
//    }
//
//
//    /**
//     * 解析函数类型
//     * @param xpsth
//     */
//    private  void analysisFunction(String xpsth){
//        List list = dom4jUtil.getDocument().selectNodes(xpsth);
//        if (list.size() > 0) {
//            Element e = (Element) list.get(0);
//            ModelViewInfo info = new ModelViewInfo();
//            info.setType(ModelViewInfo.FUNCTION);
//            info.setName(e.getQName().getName());
//            //遍历子元素,函数名称
//            for (Iterator it = e.elementIterator(); it.hasNext(); ) {
//                Element element = (Element) it.next();
//                FunctionInfo functionInfo = new FunctionInfo();
//                for (Iterator j = element.attributeIterator(); j.hasNext(); ) {//设置函数名
//                    Attribute attribute = (Attribute) j.next();
//                    functionInfo.setName(attribute.getText());
//                }
//
//                int lineNum = 0;
//                //函数的详细信息
//                for (Iterator itdetail = element.elementIterator(); itdetail.hasNext(); ) {
//                    Element detailElement = (Element) itdetail.next();
//                    if ("输入参数".equals(detailElement.getQName().getName())) {
//                        List<String> inputValues = new ArrayList<>();
//                        for (Iterator j = detailElement.attributeIterator(); j.hasNext(); ) {//设置函数名
//                            Attribute attribute = (Attribute) j.next();
//                            if (lineNum == 0) {
//                                functionInfo.addInputParameterNames(attribute.getName());
//                            }
//                            inputValues.add(attribute.getText());
//                        }
//                        functionInfo.addInputParameterValues(inputValues);
//                        lineNum++;
//                    } else if ("返回值".equals(detailElement.getQName().getName())) {
//                        for (Iterator j = detailElement.attributeIterator(); j.hasNext(); ) {//设置函数名
//                            Attribute attribute = (Attribute) j.next();
//                            if ("返回值类型".equals(attribute.getName())) {
//                                functionInfo.setReturnType(attribute.getText());
//                            } else if ("返回值描述".equals(attribute.getName())) {
//                                functionInfo.setReturnDesc(attribute.getText());
//                            }
//                        }
//                    } else if ("功能".equals(detailElement.getQName().getName())) {
//                        for (Iterator j = detailElement.attributeIterator(); j.hasNext(); ) {//设置函数名
//                            Attribute attribute = (Attribute) j.next();
//                            if ("功能描述".equals(attribute.getName())) {
//                                functionInfo.setFunctionDesc(attribute.getText());
//                            }
//                        }
//
//                    }
//                }
//                info.addFunctionInfos(functionInfo);
//            }
//            this.modelViewInfoList.add(info);
//        }
//    }
//
//    private void analysisTable(String xpsth){
//        List list = dom4jUtil.getDocument().selectNodes(xpsth);
//        if (list.size() > 0) {
//            Element e = (Element) list.get(0);
//            ModelViewInfo info = new ModelViewInfo();
//            info.setType(ModelViewInfo.TABLE);
//            info.setName(e.getQName().getName());
//            int lineNum = 0;
//            for (Iterator it = e.elementIterator(); it.hasNext(); ) {
//                Element element = (Element) it.next();
//                //遍历整个子元素的属性
//                List<String> valuss = new ArrayList<>();
//                for (Iterator j = element.attributeIterator(); j.hasNext(); ) {
//                    Attribute attribute = (Attribute) j.next();
//                    if(lineNum==0){//第一行
//                        info.addColumnNames(attribute.getName());
//                    }
//                    valuss.add(attribute.getText());
//                }
//                info.addColumnValues(valuss);
//                lineNum++;
//            }
//            this.modelViewInfoList.add(info);
//        }
//    }
//
//
//
//
//    private void analysisT() {
//        List list = dom4jUtil.getDocument().selectNodes("/Root/模型调用帧周期");
//        if (list.size() > 0) {
//            Element e = (Element) list.get(0);
//            ModelViewInfo info = new ModelViewInfo();
//            info.setType(ModelViewInfo.CHECKBOX);
//            info.setName(e.getQName().getName());
//            for (Iterator it = e.elementIterator(); it.hasNext(); ) {
//                Element element = (Element) it.next();
//                if("周期".equals(element.getQName().getName())){
//                    info.setValue(element.getText());
//                }else if("单位".equals(element.getQName().getName())){
//                    info.setValueUnit(element.getText());
//                }else if("是否可配置周期".equals(element.getQName().getName())){
//                        if("是".equals(element.getText())){
//                            info.setValueEnable(true);
//                        }else {
//                            info.setValueEnable(false);
//                        }
//                }
//
//            }
//            this.modelViewInfoList.add(info);
//        }
//    }
//
//
//    private void analysisModelInfo() {
//        //获取基本信息
//        List list = dom4jUtil.getDocument().selectNodes("/Root/模型基本信息");
//        if (list.size() > 0) {
//            Element e = (Element) list.get(0);
//            //枚举所有子节点
//            for (Iterator it = e.elementIterator(); it.hasNext(); ) {
//                Element element = (Element) it.next();
//                String eName = element.getQName().getName();
//                String text = element.getText();
//                if ("模型名称".equals(eName)) {
//                    this.modelInfo.setModelName(text);
//                } else if ("模型编写时间".equals(eName)) {
//                    this.modelInfo.setWriteTime(text);
//                } else if ("模型编写人".equals(eName)) {
//                    this.modelInfo.setDeveloper(text);
//                } else if ("模型编写单位".equals(eName)) {
//                    this.modelInfo.setModelDepartment(text);
//                } else if ("模型描述信息".equals(eName)) {
//                    this.modelInfo.setDescription(text);
//                } else if ("模型标签".equals(eName)) {
//                    this.modelInfo.setModelLabel(text);
//                }
//            }
//        }
//    }
//
//
//    /**
//     * 获取模型参数信息
//     *
//     * @return
//     */
//    public List<ModelViewInfo> getModelView() {
//
//        return this.modelViewInfoList;
//    }
//
//    /**
//     * 获取模型信息
//     *
//     * @return
//     */
//    public ModelInfo getModelInfo() {
//        return this.modelInfo;
//    }
//
//
//    public static void main(String[] args) {
//        ModelXmlEngine modelXmlService = new ModelXmlEngine("E:\\项目管理\\二院项目\\项目三\\01需求\\需求调研记录\\模型文件描述模板\\装备模型描述文件模板.xml", ModelXmlEngine.EQUIPMENT);
//        modelXmlService.getModelInfo();
//        modelXmlService.getModelView();
//    }
//}
