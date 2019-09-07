package com.snake.web.boot.module.rup.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snake.web.boot.module.rup.mapper.FlowStepMapper;
import com.snake.web.boot.module.rup.mapper.ModelInfoMapper;
import com.snake.web.boot.module.rup.mapper.ModelUserMapper;
import com.snake.web.boot.module.rup.model.ModelUser;
import com.snake.web.boot.module.rup.repository.ModelInfoRepository;
import com.snake.web.boot.module.rup.model.ModelInfo;
import com.snake.web.boot.module.rup.xmlengine.engine.ModelXmlAnalysis;
import com.snake.web.boot.module.system.model.Attachment;
import com.snake.web.boot.module.system.repository.AttachmentRepository;
import com.snake.web.boot.utils.HashMaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("modelInfoService")
public class ModelInfoService {
    @Autowired
    private ModelInfoRepository modelInfoRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private ModelInfoMapper modelInfoMapper;
    @Autowired
    private ModelUserMapper modelUserMapper;
//    @Autowired
//    private


    /**
     * 保存model信息
     *
     * @param modelInfo
     */
    public Object saveModel(ModelInfo modelInfo) {
        modelInfoRepository.save(modelInfo);
        setAttachmentName(modelInfo);
        setTesterAndEvaluator(modelInfo);
        return modelInfo;
    }

    /**
     * 获取model信息，返回分页信息
     *
     * @param keys
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getAllModelInfoByKeys(String keys, Long type, Integer pageNum, Integer pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<ModelInfo> list = modelInfoMapper.selectModelInfoByKeys(keys, type);
        return new PageInfo(list);
    }


    /**
     * 获取模型展示信息
     *
     * @param attachmentId
     * @param category
     * @return
     */
    public Map getModelInfoView(Long attachmentId, Long category) {
        ModelXmlAnalysis modelXmlService = new ModelXmlAnalysis(attachmentRepository.getOne(attachmentId).getPath());
        ModelInfo info = modelXmlService.getModelInfo();
        info.setModelFileId(attachmentId);
        setAttachmentName(info);
        setTesterAndEvaluator(info);
        info.setCategory(category);
        return HashMaps.asHashMap(new String[]{"modelInfo", "modelViews"}, new Object[]{info, modelXmlService.getModelView()});
    }

    /**
     * 模型启动
     *
     * @param modelInfo
     * @return
     */
    public Object startModel(ModelInfo modelInfo) {
        modelInfo.setModelStatus(1);
        modelInfo.setModelCode(getCreateModelCode(modelInfo));//自动生成模型编码
        //设置模型编码生成规则
        modelInfoRepository.save(modelInfo);
        setAttachmentName(modelInfo);
        setTesterAndEvaluator(modelInfo);
        return modelInfo;
    }

    /**
     * 获取编码
     * @param modelInfo
     * @return
     */
    private String getCreateModelCode(ModelInfo modelInfo) {
        Long maxId = modelInfoMapper.selectMaxId();
        //根据类型进行区分
        StringBuffer stringBuffer = new StringBuffer();
        //        1	作战模型
        //        2	装备模型
        //        3	环境模型
        //        4	评估模型
        //        5	支撑模型
        long category = modelInfo.getCategory();
        if(category==1){
            stringBuffer.append("ZZ01");
        }else if(category==2){
            stringBuffer.append("ZB02");
        }else  if(category==3){
            stringBuffer.append("HJ03");
        }else  if(category==4){
            stringBuffer.append("PG04");
        }else  if(category==5){
            stringBuffer.append("ZC05");
        }
        String c = String.valueOf(maxId);
        if(c.length()<6){
            int count = 6-c.length();
            for(int i=0;i<count;i++){
                c="0"+c;
            }
        }
        stringBuffer.append(c);
        return stringBuffer.toString();
    }


    /**
     * 模型下一步标识
     *
     * @param modelInfo
     * @return
     */
    public Object nextModel(ModelInfo modelInfo, Integer stepId) {
        modelInfo.setModelStatus(stepId);
        modelInfoRepository.save(modelInfo);
        setAttachmentName(modelInfo);
        setTesterAndEvaluator(modelInfo);
        return modelInfo;
    }

    /**
     * 获取模型信息
     *
     * @param id
     * @return
     */
    public Map getModelInfo(Long id) {
        Optional<ModelInfo> modelInfo = modelInfoRepository.findById(id);
        ModelInfo info = modelInfo.get();
        setAttachmentName(info);
        setTesterAndEvaluator(info);
        Map map = new HashMap();
        map.put("modelInfo", modelInfo);
        if (info.getModelFileId() != null) {
            ModelXmlAnalysis modelXmlService = new ModelXmlAnalysis(attachmentRepository.getOne(info.getModelFileId()).getPath());
            map.put("modelViews", modelXmlService.getModelView());
        }
        return map;
    }


    /**
     * 获取模型信息
     *
     * @param id
     * @return
     */
    public ModelInfo getModelInfoById(Long id) {
        Optional<ModelInfo> modelInfo = modelInfoRepository.findById(id);
        ModelInfo info = modelInfo.get();
        setAttachmentName(info);
        setTesterAndEvaluator(info);
        return info;
    }


    /**
     * 设置模型附件信息
     *
     * @param modelInfo
     */
    private void setAttachmentName(ModelInfo modelInfo) {
        if (modelInfo.getModelFileId() != null) {
            Optional<Attachment> modelFile = attachmentRepository.findById(modelInfo.getModelFileId());// 模型描述文件id
            if (modelFile.isPresent()) {
                modelInfo.setModelFileName(modelFile.get().getOldName());
            }
        }
        if (modelInfo.getModelManualId() != null) {
            Optional<Attachment> modelManual = attachmentRepository.findById(modelInfo.getModelManualId());//模型使用手册id
            if (modelManual.isPresent()) {
                modelInfo.setModelManualName(modelManual.get().getOldName());
            }
        }
        if (modelInfo.getModelTestReportId() != null) {
            Optional<Attachment> modelTest = attachmentRepository.findById(modelInfo.getModelTestReportId());// 模型测试报告
            if (modelTest.isPresent()) {
                modelInfo.setModelTestReportName(modelTest.get().getOldName());
            }
        }
        if (modelInfo.getModelAssessmentReportId() != null) {
            Optional<Attachment> modelAssessment = attachmentRepository.findById(modelInfo.getModelAssessmentReportId());// 模型可信度评估报告
            if (modelAssessment.isPresent()) {
                modelInfo.setModelAssessmentReportName(modelAssessment.get().getOldName());
            }
        }
    }

    /**
     * 设置测试人员和评估人员
     *
     * @param modelInfo
     */
    private void setTesterAndEvaluator(ModelInfo modelInfo) {
        if (modelInfo.getId() > 0) {
            Example ex = new Example(ModelUser.class);
            ex.createCriteria().andEqualTo("modelId", modelInfo.getId()).andEqualTo("userType", 1);
            modelInfo.setTesters(modelUserMapper.selectByExample(ex));
            Example ex1 = new Example(ModelUser.class);
            ex1.createCriteria().andEqualTo("modelId", modelInfo.getId()).andEqualTo("userType", 0);
            modelInfo.setEvaluators(modelUserMapper.selectByExample(ex));
        }
    }

    public List<ModelInfo> getMyModelInfo(Long loginUserId) {
        return modelInfoRepository.findByDeveloperId(loginUserId);
    }


    /**
     * 我的收藏
     *
     * @param loginUserId
     * @return
     */
    public List<ModelInfo> getMyCollections(Long loginUserId) {
        return modelInfoMapper.selectMyCollections(loginUserId);
    }

    /**
     * 我的下载
     *
     * @param loginUserId
     * @return
     */
    public List<ModelInfo> getMyDowns(Long loginUserId) {
        return modelInfoMapper.selectMyDowns(loginUserId);
    }

    /**
     * 我的
     *
     * @param loginUserId
     * @return
     */
    public List<ModelInfo> getMyUploads(Long loginUserId) {
        return modelInfoMapper.selectMyUploads(loginUserId);
    }

    /**
     * 我的待入库
     *
     * @param loginUserId
     * @param category
     * @return
     */
    public List<ModelInfo> getMyWarehousing(Long loginUserId, Long category) {
        return modelInfoMapper.selectMyWarehousing(loginUserId, category);
    }


    /**
     * 我的已入库
     *
     * @param loginUserId
     * @param category
     * @param keys
     * @return
     */
    public List<ModelInfo> myCompletedWarehousing(Long loginUserId, Long category, String keys) {
        return modelInfoMapper.myCompletedWarehousing(loginUserId, category, keys);
    }


    /**
     * 设置小类
     *
     * @param id
     * @param type
     */
    public boolean setType(Long id, Long type) {
        Optional<ModelInfo> modelInfo = modelInfoRepository.findById(id);
        ModelInfo info = modelInfo.get();
        if (info != null) {
            info.setType(type);
            modelInfoMapper.updateByPrimaryKey(info);
        } else {
            return false;
        }
        return true;
    }


}
