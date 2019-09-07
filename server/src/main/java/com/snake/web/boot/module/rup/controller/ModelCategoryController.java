package com.snake.web.boot.module.rup.controller;

import com.snake.web.boot.config.ApiResult;
import com.snake.web.boot.module.rup.mapper.ModelCategoryMapper;
import com.snake.web.boot.module.rup.model.ModelCategory;
import com.snake.web.boot.module.system.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/modelCategory/")
public class ModelCategoryController {
    @Autowired
    private ModelCategoryMapper modelCategoryMapper ;
    @Autowired
    private AttachmentRepository attachmentRepository ;
    /**
     * 获取根节点
     * @return
     */
    @RequestMapping("getRoot")
    Object getRoot(){
        return getRootList();
    }

    /**
     * 获取所有
     * @return
     */
    @RequestMapping("getAll")
    Object getAll(){
      return   modelCategoryMapper.selectAll();
    }

    @RequestMapping("getChildren")
    Object getChildren(Long id){
        return getChildList(id);
    }

    private List<ModelCategory>  getChildList(Long id) {
        Example example = new Example(ModelCategory.class);
        example.createCriteria().andEqualTo("parentId",id);
        example.setOrderByClause("id desc");
        List<ModelCategory> list = modelCategoryMapper.selectByExample(example);
        if(list==null){
            list = new ArrayList<>();
        }
        return  list ;
    }

    /**
     * 更新信息
     * @param modelCategory
     * @return
     */
    @RequestMapping("update")
    Object update(@RequestBody  ModelCategory modelCategory){
        modelCategoryMapper.updateByPrimaryKey(modelCategory);
        return  modelCategory ;
    }

    /**
     * 增加分类
     * @param modelCategory
     * @return
     */
    @RequestMapping("add")
    Object  add(@RequestBody  ModelCategory modelCategory){
        modelCategoryMapper.insert(modelCategory);
        return  modelCategory ;
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping("delete")
    Object  delete(Long id){
        modelCategoryMapper.deleteByPrimaryKey(id);
        return ApiResult.success("删除成功");
    }


    /**
     * 获取描述信息分类描述文件
     * @return
     */
    @RequestMapping("getDescriptionFile")
    List<ModelCategory> getDescriptionFile(){
        List<ModelCategory> rootList = getRootList();
        List<ModelCategory> child = getChildList(4l);
        List rList = new ArrayList();
        for(ModelCategory mc:rootList){
            if(mc.getFileId()!=null){
                mc.setFile(attachmentRepository.findById(mc.getFileId()).get());
            }
            rList.add(mc);
        }

        for(ModelCategory mc:child){
            if(mc.getFileId()!=null){
                mc.setFile(attachmentRepository.findById(mc.getFileId()).get());
            }
            rList.add(mc);
        }
        return rList;
    }

    private List<ModelCategory>  getRootList() {
        Example example = new Example(ModelCategory.class);
        example.createCriteria().andEqualTo("parentId", 0);
        example.setOrderByClause("id asc");
        List<ModelCategory> list = modelCategoryMapper.selectByExample(example);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }


}
