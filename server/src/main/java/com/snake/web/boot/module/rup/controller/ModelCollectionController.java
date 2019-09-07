package com.snake.web.boot.module.rup.controller;

import com.snake.web.boot.config.ApiResult;
import com.snake.web.boot.module.controller.BasicController;
import com.snake.web.boot.module.rup.mapper.ModelCollectionMapper;
import com.snake.web.boot.module.rup.model.ModelCollection;
import com.snake.web.boot.module.rup.model.ModelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/model/")
public class ModelCollectionController extends BasicController {

    @Autowired
    private ModelCollectionMapper modelCollectionMapper ;

    /**
     *记录模型收藏
     * @param modelId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modelCollection")
    Object modelCollection(Long modelId){
        ModelCollection modelCollection = new ModelCollection();
         modelCollection.setModelId(modelId);
        modelCollection = modelCollectionMapper.selectOne(modelCollection);
        if(modelCollection!=null){
            return  modelCollection;
        }else {
            modelCollection = new ModelCollection();
            modelCollection.setModelId(modelId);
            modelCollection.setCreateTime(new Date());
            modelCollection.setUserId(loginUser().getId());
            modelCollection.setUserName(loginUser().getName());
            modelCollection.setCollectStatus(true);
            modelCollectionMapper.insert(modelCollection);
        }
        return  modelCollection ;
    }

    /**
     *取消模型收藏
     * @param modelId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modelCancelCollection")
    Object modelCancelCollection(Long modelId){
        ModelCollection modelCollection = new ModelCollection();
        modelCollection.setModelId(modelId);
        modelCollection = modelCollectionMapper.selectOne(modelCollection);
        if(modelCollection!=null){
            modelCollectionMapper.delete(modelCollection);
        }
        return ApiResult.success("取消收藏成功");
    }

}
