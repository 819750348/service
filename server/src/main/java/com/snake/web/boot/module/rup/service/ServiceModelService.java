package com.snake.web.boot.module.rup.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snake.web.boot.module.rup.mapper.ServiceModelMapper;
import com.snake.web.boot.module.rup.model.ModelInfo;
import com.snake.web.boot.module.rup.model.ServiceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceModelService")
public class ServiceModelService {
    @Autowired
    ServiceModelMapper serviceModelMapper;
/*
* 获取模型列表
* */
    public PageInfo getServiceModelListAll(String keys,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<ServiceInfo> list = serviceModelMapper.getServiceModelListAll(keys);
        for(int i=0;i<list.size();i++){
               if(list.get(i).getDesignerStatus()==null){
                   list.get(i).setDesignerStatus("");
               }else if
                       (list.get(i).getDesignerStatus().equals("0")){
                    list.get(i).setDesignerStatus("草稿");
                }else {
                    list.get(i).setDesignerStatus("提交");
                }

        }
        return new PageInfo(list);
    }

    public List<ServiceInfo> selectLabelServiceUsers(String labelid,String keys){

        List<ServiceInfo> list = serviceModelMapper.selectLabelServiceUsers(labelid,keys);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDesignerStatus()==null){
                list.get(i).setDesignerStatus("");
            }else if
                    (list.get(i).getDesignerStatus().equals("0")){
                list.get(i).setDesignerStatus("草稿");
            }else {
                list.get(i).setDesignerStatus("提交");
            }

        }
        return list;
    }

    public List<ServiceInfo> searchServicekeys(String keys){

       List<ServiceInfo> list=serviceModelMapper.searchServicekeys(keys);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDesignerStatus()==null){
                list.get(i).setDesignerStatus("");
            }else if
                    (list.get(i).getDesignerStatus().equals("0")){
                list.get(i).setDesignerStatus("草稿");
            }else {
                list.get(i).setDesignerStatus("提交");
            }

        }
       return list;
    }
}
