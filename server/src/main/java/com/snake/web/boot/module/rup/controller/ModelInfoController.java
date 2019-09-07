package com.snake.web.boot.module.rup.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snake.web.boot.config.ApiResult;
import com.snake.web.boot.config.ApiResultType;
import com.snake.web.boot.module.controller.BasicController;
import com.snake.web.boot.module.rup.mapper.ModelUserMapper;
import com.snake.web.boot.module.rup.model.ModelInfo;
import com.snake.web.boot.module.rup.model.ModelUser;
import com.snake.web.boot.module.rup.service.FlowInstService;
import com.snake.web.boot.module.rup.service.ModelInfoService;
import com.snake.web.boot.module.rup.xmlengine.engine.ModelXmlAnalysis;
import com.snake.web.boot.module.system.model.Attachment;
import com.snake.web.boot.module.system.model.User;
import com.snake.web.boot.module.system.repository.AttachmentRepository;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/model/")
public class ModelInfoController extends BasicController {
    @Autowired
    ModelInfoService modelInfoService;
    @Autowired
    private ModelUserMapper modelUserMapper;

    @Autowired
    private FlowInstService flowInstService;
    @Autowired
    private AttachmentRepository attachmentRepository ;

    /**
     * 获取模型基本信息
     *
     * @param category
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getModelInfoView")
    Object getModelInfoView(Long attachmentId, Long category) {
        return modelInfoService.getModelInfoView(attachmentId, category);
    }


    /**
     * 模型启动
     *
     * @param modelInfo
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "startModel")
    Object startModel(@RequestBody ModelInfo modelInfo) {
        userInit(modelInfo, loginUser());
        Object startModel = modelInfoService.startModel(modelInfo);
        return startModel;
    }


    /**
     * @param modelInfo
     * @param user
     */
    private void userInit(ModelInfo modelInfo, User user) {
        modelInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        modelInfo.setDeveloperId(user.getId());
        modelInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        modelInfo.setUpdateUser(user.getUsername());
        modelInfo.setUpdateUserId(user.getId());
    }


    /**
     * 更新用户信息
     *
     * @param modelInfo
     * @param user
     */
    private void userUpdate(ModelInfo modelInfo, User user) {
        modelInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        modelInfo.setUpdateUser(user.getUsername());
        modelInfo.setUpdateUserId(user.getId());
    }


    /**
     * 模型下一步标识
     *
     * @param modelInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "saveModel")
    Object saveModel(@RequestBody ModelInfo modelInfo) {
        userInit(modelInfo, loginUser());
        return modelInfoService.saveModel(modelInfo);
    }

    /**
     * 模型下一步标识
     *
     * @param modelInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "nextModel")
    Object nextModel(@RequestBody ModelInfo modelInfo, Integer stepId) {
        userInit(modelInfo, loginUser());
        return modelInfoService.nextModel(modelInfo, stepId);
    }

    /**
     * 模型提交
     *
     * @param modelInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "commitModel")
    Object commitModel(@RequestBody ModelInfo modelInfo) {
        userInit(modelInfo, loginUser());
        flowInstService.startFlow(flowInstService.MODEL_FLOW, modelInfo.getId(), loginUser());
        return modelInfoService.nextModel(modelInfo, 5);
    }

    /**
     * 获取模型信息
     *
     * @param id 模型id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getModelInfo")
    Object getModelInfo(Long id) {
        Map map = modelInfoService.getModelInfo(id);
        return map;
    }

    /**
     * 我的上传
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMyUploadList")
    Object getMyUploadList() {
        return modelInfoService.getMyModelInfo(loginUserId());
    }


    @ResponseBody
    @RequestMapping(value = "getAllModelInfoByKeys")
    Object getAllModelInfoByKeys
            (
                    @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                            int pageNum,
                    @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                            int pageSize,
                    @RequestBody JSONObject param
            ) {

        String keys = "";
        if (param.containsKey("keys")) {
            keys = param.getString("keys");
        }
        Long type = null;
        if (param.containsKey("type")) {
            type = param.getLong("type");
        }
        return modelInfoService.getAllModelInfoByKeys(keys, type, pageNum, pageSize);
    }

    /**
     * 我的收藏
     *
     * @param
     * @return
     */
    @RequestMapping(value = "myCollections")
    Object myCollections(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                 int pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                 int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ModelInfo> list = modelInfoService.getMyCollections(loginUserId());
        return new PageInfo(list);
    }

    /**
     * 我的下载,根据当前用户获取下载信息
     *
     * @return
     */
    @RequestMapping(value = "myDownloads")
    Object myDownloads(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                               int pageNum,
                       @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                               int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ModelInfo> list = modelInfoService.getMyDowns(loginUserId());
        return new PageInfo(list);
    }

    /**
     * 我的上传
     *
     * @return
     */
    @RequestMapping(value = "myUploads")
    Object myUploads(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                             int pageNum,
                     @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                             int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ModelInfo> list = modelInfoService.getMyUploads(loginUserId());
        return new PageInfo(list);
    }


    /**
     * 我的待入库
     *
     * @return
     */
    @RequestMapping(value = "myWaitWarehousing")
    Object myWaitWarehousing(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                     int pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                     int pageSize, Long category) {
        PageHelper.startPage(pageNum, pageSize);
        List<ModelInfo> list = modelInfoService.getMyWarehousing(loginUserId(), category);
        return new PageInfo(list);
    }

    /**
     * 我的已入库
     *
     * @return
     */
    @RequestMapping(value = "myCompletedWarehousing")
    Object myCompletedWarehousing(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                          int pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                          int pageSize, Long category, @RequestBody JSONObject param) {
        String keys = "";
        if (param.containsKey("keys")) {
            keys = param.getString("keys");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ModelInfo> list = modelInfoService.myCompletedWarehousing(loginUserId(), category, keys);
        return new PageInfo(list);
    }

    /**
     * 增加用户
     *
     * @return
     */
    @RequestMapping(value = "addModelUser")
    Object addModelUser(@RequestBody ModelUser modelUser) {
        modelUserMapper.insert(modelUser);
        return modelUser;
    }

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping(value = "deleteModelUser")
    Object deleteModelUser(@RequestBody ModelUser modelUser) {
        modelUserMapper.delete(modelUser);
        return modelUser;
    }


    /**
     * 设置小类
     *
     * @return
     */
    @RequestMapping(value = "setType")
    Object setType(Long id, Long type) {
        boolean b = modelInfoService.setType(id, type);
        if (b) {
            return ApiResult.success("设置成功");
        }
        return ApiResult.error(ApiResultType.BUSINESS_ERROR, "设置失败");
    }

    @RequestMapping(value = "saveModelXml")
    public Object saveModelXml(@RequestBody ModelInfo modelInfo,@RequestBody List<JSONObject> vl){
        if(modelInfo.getModelFileId()==null){//表示描述文件还没有上传
//            attachmentRepository.save();
//        }else{
        }
        Attachment attachment = attachmentRepository.getOne(modelInfo.getModelFileId());
        ModelXmlAnalysis.saveXml(modelInfo,vl,attachment.getPath());
        return  null ;
    }
}
