package com.snake.web.boot.module.rup.service;

import com.github.pagehelper.PageInfo;
import com.snake.web.boot.module.rup.mapper.ModelInfoMapper;
import com.snake.web.boot.module.rup.model.ModelInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelInfoServiceTest {
    @Autowired
    private ModelInfoService modelInfoService;
    @Autowired
    private ModelInfoMapper modelInfoMapper;
    @Test
    public void saveModel() {
    }

    @Test
    @Transactional
    @Rollback(true)
    public void getAllModelInfoByKeys() {
        for (int i = 0; i < 20; i++) {
            ModelInfo modelInfo=new ModelInfo();
            modelInfo.setModelName("name"+i);
            modelInfoMapper.insert(modelInfo);
        }
        PageInfo inf = modelInfoService.getAllModelInfoByKeys("", 0L, 1, 20);
        Assert.assertEquals(20,inf.getSize());
        Assert.assertEquals(20,inf.getList().size());
//        PageInfo<ModelInfo> l = modelInfoService.getAllModelInfoByKeys("测试", 1, 0);
//        List<ModelInfo> infoList = l.getList();
    }

    @Test
    public void getModelViewInfo() {
    }

    @Test
    public void startModel() {
    }

    @Test
    public void nextModel() {
    }

    @Test
    public void getModelInfo() {
    }

    @Test
    public void getMyModelInfo() {
    }

}