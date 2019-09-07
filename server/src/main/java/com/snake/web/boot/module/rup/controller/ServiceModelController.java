package com.snake.web.boot.module.rup.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snake.web.boot.module.rup.mapper.ServiceCategoryMapper;
import com.snake.web.boot.module.rup.mapper.ServiceModelMapper;
import com.snake.web.boot.module.rup.model.ServiceCategory;
import com.snake.web.boot.module.rup.model.ServiceInfo;
import com.snake.web.boot.module.rup.service.ServiceModelService;
import com.snake.web.boot.module.system.model.User;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@RequestMapping("/all/")
public class ServiceModelController {

    @Autowired
    ServiceModelMapper serviceModelMapper;
    @Autowired
    ServiceModelService serviceModelService;
    @Autowired
    ServiceCategoryMapper serviceCategoryMapper;
    /*
    * 提交新添加服务内容
    * */
    @RequestMapping(value = "addorupdateservice")
    public Object addorupdateservice(@RequestBody ServiceInfo serviceInfo){
        serviceInfo.setDesignerStatus("1");
        if(serviceInfo.getId()!=0){

           serviceModelMapper.updateByPrimaryKey(serviceInfo);


        }else {


            serviceModelMapper.insert(serviceInfo);

        }
        String keys = "";
        int pageNum = 1;
        int pageSize = 20;
        return serviceModelService.getServiceModelListAll(keys, pageNum, pageSize);
    }


    /*
     * 保存新添加服务内容
     * */
    @RequestMapping(value = "saveaddorupdateservice")
    public Object saveaddorupdateservice(@RequestBody ServiceInfo serviceInfo){
        serviceInfo.setDesignerStatus("0");
        if(serviceInfo.getId()!=0){

            serviceModelMapper.updateByPrimaryKey(serviceInfo);

        }else {
            serviceModelMapper.insert(serviceInfo);

        }
        String keys = "";
        int pageNum = 1;
        int pageSize = 20;
        return serviceModelService.getServiceModelListAll(keys, pageNum, pageSize);
    }



    /*
     * 获取模型列表
     * */
    @RequestMapping(value = "getServiceModelListAll")
    public Object getServiceModelListAll(  @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                       int pageNum,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                                       int pageSize,
                                           @RequestBody JSONObject param)
    {
        String keys = "";
        if (param.containsKey("keys")) {
            keys = param.getString("keys");
        }

        return serviceModelService.getServiceModelListAll(keys, pageNum, pageSize);
    }
    /*
    * 添加标签分类
    * */
      @RequestMapping("addServiceLabel")
    public Object addServiceLabel(@RequestBody ServiceCategory label){
          serviceCategoryMapper.insert(label);
          return label;
      }

      /*
      * 获取标签列表
      * */
      @RequestMapping("getServiceLabels")
    public Object getServiceLabels(){

          return serviceCategoryMapper.selectAll();
      }


    /*
    *根据标签id选择用户
    *
     */
    @RequestMapping("getLabelServiceUser")
    public Object getLabelServiceUser(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                                  int pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                                  int pageSize, @RequestBody JSONObject param){

        PageHelper.startPage(pageNum, pageSize);

        String keys = "" ;
        if(param.containsKey("keys")){
            keys = param.getString("keys");
        }
        String labelid = "";
        if(param.containsKey("labelid")){
            labelid = param.getString("labelid") ;
        }
        List<ServiceInfo> users = serviceModelService.selectLabelServiceUsers(labelid,keys);
        return new PageInfo(users);
    }
    /*
    * 根据关键字搜索服务
    *
    * */
    @RequestMapping("searchServicekeys")
    public Object searchServicekeys(@RequestBody JSONObject param ){
        String keys = "";

        if(param.containsKey("searchKeys")){
            keys = param.getString("searchKeys");
        }
        List<ServiceInfo> users=serviceModelService.searchServicekeys(keys);
        return new PageInfo(users);
    }
    /*
     * 删除服务
     *
     * */
    @RequestMapping("deleteService")
    public Object deleteService(@RequestBody ServiceInfo serviceInfo ){

//        serviceModelMapper.deleteByPrimaryKey(serviceInfo.getId());
        Example example=new Example(ServiceInfo.class);
        example.createCriteria().andEqualTo("id",serviceInfo.getId());

        serviceModelMapper.deleteByExample(example);


        String keys="";
         int pageNum=1;
        int pageSize=20;
        return serviceModelService.getServiceModelListAll(keys, pageNum, pageSize);
    }

    /*
     * 开发服务
     *
     * */
    @RequestMapping("devservice")
    public Object devservice(@RequestBody ServiceInfo serviceInfo ){


        Example example=new Example(ServiceInfo.class);
        example.createCriteria().andEqualTo("id",serviceInfo.getId());

        serviceModelMapper.updateByExample(serviceInfo,example);


        String keys="";
        int pageNum=1;
        int pageSize=20;
        return serviceModelService.getServiceModelListAll(keys, pageNum, pageSize);
    }
}
