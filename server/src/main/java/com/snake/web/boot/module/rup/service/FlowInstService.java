package com.snake.web.boot.module.rup.service;

import com.snake.web.boot.module.rup.mapper.BusinessUserMapper;
import com.snake.web.boot.module.rup.mapper.FlowInstMapper;
import com.snake.web.boot.module.rup.mapper.FlowStepMapper;
import com.snake.web.boot.module.rup.model.*;
import com.snake.web.boot.module.rup.repository.BasicParamRepository;
import com.snake.web.boot.module.rup.repository.ModelInfoRepository;
import com.snake.web.boot.module.system.model.User;
import com.snake.web.boot.module.system.repository.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.DateFormat;
import java.util.*;

@Service("flowInstService")
public class FlowInstService {

    @Autowired
    private FlowInstMapper flowInstMapper;
    @Autowired
    private FlowStepMapper flowStepMapper;
    @Autowired
    private ModelInfoService modelInfoService;
    @Autowired
    private ModelInfoRepository  modelInfoRepository ;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusinessUserMapper businessUserMapper;

    @Autowired
    private BasicParamRepository basicParamRepository ;

    //模型流程
    public static final int MODEL_FLOW = 0;
    //申请权限流程
    public static final int APPLY_FLOW = 1;
   public  List<FlowInst> getFlows(Long userId,int type){
        List<FlowInst> list = new ArrayList<>();
        if(type==1){
            list =  flowInstMapper.selectFlowsWait(userId);
        }else if(type==2){
            list =  flowInstMapper.selectFlowsComplete(userId);
        }
        return  list ;
    }

    public  List<FlowInst> getApplies(Long userId,int type){
        List<FlowInst> list = new ArrayList<>();
        if(type==1){//表示未完成
            Integer[] waitArray = {0,1};
           Example example = new Example(FlowInst.class);
           example.createCriteria().andEqualTo("createUserid",userId).andEqualTo("flowType",1)
                   .andIn("flowStatus", Arrays.asList(waitArray));
           example.setOrderByClause("id desc"); //排序
            list = flowInstMapper.selectByExample(example);
       }else if(type==2){//表示已完成
            Integer[] completeArray = {2,3};
            Example example = new Example(FlowInst.class);
            example.createCriteria().andEqualTo("createUserid",userId).andEqualTo("flowType",1)
            .andIn("flowStatus", Arrays.asList(completeArray));
            example.setOrderByClause("id desc"); //排序
            list = flowInstMapper.selectByExample(example);
       }
        return  list ;
    }


    public List<FlowStep> getFlowStep(long bid,Integer flowType) {
        List<FlowStep> list = new ArrayList<>();
        FlowInst flowInst = new FlowInst();
        flowInst.setBusinessId(bid);
        flowInst.setFlowType(flowType);
        flowInst = flowInstMapper.selectOne(flowInst);
        if (flowInst != null && flowInst.getId() != null) {
            Example example = new Example(FlowStep.class);
            example.createCriteria().andEqualTo("flowId", flowInst.getId());
            example.setOrderByClause("step_number asc"); //排序
            list = flowStepMapper.selectByExample(example);
        }
        return list;
    }


    public FlowStep  approveFlow(FlowStep flowStep){
        flowStep.setCompleteTime(new Date());
        flowStep.setStepStatus(EnumFlowStatus.APPROVE.code);
        flowStepMapper.updateByPrimaryKeySelective(flowStep);
        Example example = new Example(FlowStep.class);
        example.createCriteria().andEqualTo("flowId", flowStep.getFlowId()).andGreaterThan("stepNumber",flowStep.getStepNumber());
        example.setOrderByClause("step_number asc"); //排序
        List<FlowStep> list = flowStepMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            FlowStep next = list.get(0);
            next.setCompleteTime(new Date());
            next.setStepStatus(EnumFlowStatus.WAIT.code);
            flowStepMapper.updateByPrimaryKeySelective(next);
        }else {
            FlowInst inst = flowInstMapper.selectByPrimaryKey(flowStep.getFlowId());
            inst.setFlowStatus(EnumFlowStatus.APPROVE.code);
            inst.setUpdateTime(new Date());
            flowInstMapper.updateByPrimaryKey(inst);
            //更新业务为8
            Optional<ModelInfo> oInfo = modelInfoRepository.findById(inst.getBusinessId());
            ModelInfo info = oInfo.get();
            info.setModelStatus(8);
            modelInfoRepository.save(info);
        }
        return  flowStep ;
    }


    public FlowStep  refuseFlow(FlowStep flowStep){
        flowStep.setCompleteTime(new Date());
        flowStep.setStepStatus(EnumFlowStatus.REFUSE.code);
        flowStepMapper.updateByPrimaryKeySelective(flowStep);
        FlowInst inst = flowInstMapper.selectByPrimaryKey(flowStep.getFlowId());
        inst.setFlowStatus(EnumFlowStatus.REFUSE.code);
        inst.setUpdateTime(new Date());
        flowInstMapper.updateByPrimaryKey(inst);
        //更新业务为9拒绝
        Optional<ModelInfo> oInfo = modelInfoRepository.findById(inst.getBusinessId());
        ModelInfo info = oInfo.get();
        info.setModelStatus(9);
        modelInfoRepository.save(info);
        return  flowStep ;
    }


    /**
     * @param flowType 流程类型
     * @param bid      业务id
     */
    public void startFlow(int flowType, long bid, User user) {
        if (flowType == MODEL_FLOW) {//模型流程
            createModelFlow(bid, user);
        }else if(flowType==APPLY_FLOW){//申请权限流程
            createApplyFlow(bid,user);
        }
    }

    /**
     * 创建权限申请流程
     * @param bid
     * @param user
     */
    private  void createApplyFlow(Long bid,User user){
        //获取,模型信息
        ModelInfo info = modelInfoService.getModelInfoById(bid);
        Date date = new Date();
        FlowInst flowInst = new FlowInst();
        flowInst.setBusinessId(bid);
        flowInst.setCreateTime(new Date());
        flowInst.setCreateUserid(user.getId());
        Date d = new Date();
        String s =  DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        flowInst.setFlowName(s+user.getName()+"申请下载"+info.getModelName()+",需要您的审核");
        flowInst.setFlowStatus(EnumFlowStatus.DRAFT.code);
        flowInst.setFlowType(1);
        flowInstMapper.insert(flowInst);
        flowStepMapper.insertList(createApplyFlowStep(flowInst.getId(), user, bid,info));
    }

    /**
     * 创建资源下载流程
     * @param fid
     * @param user
     * @param bid
     * @param info
     * @return
     */
    private List<FlowStep> createApplyFlowStep(long fid, User user, Long bid,ModelInfo info ) {
        Integer downloadSet = 0;
        List<BasicParam> basicList = basicParamRepository.findAll();
        if(basicList.size()>0){
             downloadSet = basicList.get(0).getResourceDownloadSet();
        }
        List<FlowStep> fList = new ArrayList<FlowStep>();
        Date date = new Date();
        //第四个节点 系统管理员
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("isAdmin", 1);
        example.setOrderByClause("id desc"); //排序
        List<User> users = businessUserMapper.selectByExample(example);
        String adminId = ",";
        String adminName = "";
        if (users != null && users.size() > 0) {
            for (User admin : users) {
                adminId = adminId+admin.getId() + ",";
                adminName = adminName+admin.getName() + ",";
            }
        }
        //第0个节点，启动节点
        fList.add(createModelFirstNode(fid, user, date));
        if(downloadSet==0){// 0: 申请人-->上传者
            fList.add(getFlowStep(fid, "模型开发人员审核", 1, date, ","+info.getDeveloperId()+",", info.getDeveloper(),EnumFlowStatus.WAIT.code));
        }else if(downloadSet==1){//  1: 申请人-->管理员
            if (!StringUtils.isEmpty(adminName)) {
                fList.add(getFlowStep(fid, "管理员审核", 1, date, adminId, adminName.substring(0, adminName.length() - 1),EnumFlowStatus.DRAFT.code));
            }
        }else if(downloadSet==2){//2: 申请人-->上传者-->管理员
            fList.add(getFlowStep(fid, "模型开发人员审核", 1, date, ","+info.getDeveloperId()+",", info.getDeveloper(),EnumFlowStatus.WAIT.code));
            if (!StringUtils.isEmpty(adminName)) {
                fList.add(getFlowStep(fid, "管理员审核", 2, date, adminId, adminName.substring(0, adminName.length() - 1),EnumFlowStatus.DRAFT.code));
            }
        }
        return fList;
    }


    private void createModelFlow(long bid, User user) {
        Date date = new Date();
        FlowInst flowInst = new FlowInst();
        flowInst.setBusinessId(bid);
        flowInst.setCreateTime(new Date());
        flowInst.setCreateUserid(user.getId());
        Date d = new Date();
        String s =  DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        flowInst.setFlowName(s+user.getName()+"提交的模型需要您的审核");
        flowInst.setFlowStatus(EnumFlowStatus.DRAFT.code);
        flowInst.setFlowType(0);
        flowInstMapper.insert(flowInst);
        flowStepMapper.insertList(createModelFlowStep(flowInst.getId(), user, bid));
    }

    private List<FlowStep> createModelFlowStep(long fid, User user, Long bid) {
        List<FlowStep> fList = new ArrayList<FlowStep>();
        Date date = new Date();
        //第0个节点，启动节点
        fList.add(createModelFirstNode(fid, user, date));
        ModelInfo info = modelInfoService.getModelInfoById(bid);

        //第一个节点 测试人员
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("isTester", 1);
        example.setOrderByClause("id desc"); //排序
        List<User> tseters = businessUserMapper.selectByExample(example);
        String testUserId = ",";
        String testUserName = "";
        long category = info.getCategory() ;
        for (User tester : tseters) {
            if(isTester(category,tester)){
                testUserId =testUserId+ tester.getId() + ",";
                testUserName = testUserName+tester.getUsername() + ",";
            }
        }
        if (!StringUtils.isEmpty(testUserName)) {
            fList.add(getFlowStep(fid, "测试审核", 1, date, testUserId, testUserName.substring(0, testUserName.length() - 1),EnumFlowStatus.WAIT.code));
        }
        //第二个节点 评估人员
        Example example2 = new Example(User.class);
        example2.createCriteria().andEqualTo("isEvaluator", 1);
        example2.setOrderByClause("id desc"); //排序
        List<User> evaluators = businessUserMapper.selectByExample(example2);
        String evaluatorId = ",";
        String evaluatorName = "";
        for (User evaluator : evaluators) {
            if(isEvaluation(category,evaluator)) {
                evaluatorId = evaluatorId+evaluator.getId() + ",";
                evaluatorName =evaluatorName+ evaluator.getUsername() + ",";
            }
        }
        if (!StringUtils.isEmpty(evaluatorName)) {
            fList.add(getFlowStep(fid, "可信度评估审核", 2, date, evaluatorId, evaluatorName.substring(0, evaluatorName.length() - 1),EnumFlowStatus.DRAFT.code));
        }
        //第三个 部门领导
        List<User> leaders = userRepository.findByDepartmentAndIsLeader(user.getDepartment(), true);
        String leaderId = ",";
        String leaderName = "";
        if (leaders != null && leaders.size() > 0) {
            for (User leader : leaders) {
                leaderId = leaderId+leader.getId() + ",";
                leaderName = leaderName+leader.getName() + ",";
            }
            if (!StringUtils.isEmpty(leaderName)) {
                fList.add(getFlowStep(fid, "部门领导审核", 3, date, leaderId, leaderName.substring(0, leaderName.length() - 1),EnumFlowStatus.DRAFT.code));
            }
        }


        Example example1 = new Example(User.class);
        example1.createCriteria().andEqualTo("isAdmin", 1);
        example1.setOrderByClause("id desc"); //排序
        List<User> users = businessUserMapper.selectByExample(example1);

        String adminId = ",";
        String adminName = "";

        //第四个节点 系统管理员
        if (users != null && users.size() > 0) {
            for (User admin : users) {
                adminId = adminId+admin.getId() + ",";
                adminName = adminName+admin.getName() + ",";
            }
            if (!StringUtils.isEmpty(adminName)) {
                fList.add(getFlowStep(fid, "系统管理员入库", 4, date, adminId, adminName.substring(0, adminName.length() - 1),EnumFlowStatus.DRAFT.code));
            }
        }
        return fList;
    }

    /**
     * 判断人员是否为测试人员
     * @param category
     * @param user
     * @return
     */
    private boolean isTester(Long category,User user){
//        1	作战模型
//        2	装备模型
//        3	环境模型
//        4	评估模型
//        5	支撑模型
     if(category==1){
         return  user.getTestCambot();
     }else if(category==2){
         return  user.getTestEquipment();
     }else  if(category==3){
         return  user.getTestEnvironment();
     }else  if(category==4){
         return  user.getTestEvaluation();
     }else  if(category==5){
         return  user.getTestSupport();
     }
        return  false ;
    }


    /**
     * 判断人员是否为评估人员
     * @param category
     * @param user
     * @return
     */
    private boolean isEvaluation(Long category,User user){
//        1	作战模型
//        2	装备模型
//        3	环境模型
//        4	评估模型
//        5	支撑模型
        if(category==1){
            return  user.getEvaluateCambot();
        }else if(category==2){
            return  user.getEvaluateEquipment();
        }else  if(category==3){
            return  user.getEvaluateEnvironment();
        }else  if(category==4){
            return  user.getEvaluateEvaluation();
        }else  if(category==5){
            return  user.getEvaluateSupport();
        }
        return  false ;
    }

    private FlowStep createModelFirstNode(long fid, User user, Date date) {
        //第一个节点,批量创建节点
        FlowStep flowStep = new FlowStep();
        flowStep.setFlowId(fid);
        flowStep.setStepName("流程启动");
        flowStep.setDoWithUserid(user.getId());
        flowStep.setDoWithUser(user.getUsername());
        flowStep.setWaitUserId(String.valueOf(user.getId()));
        flowStep.setWaitUser(user.getUsername());
        flowStep.setApprovalOpinion("提交审核");
        flowStep.setCreateTime(date);
        flowStep.setCompleteTime(date);
        flowStep.setStepNumber(0);
        flowStep.setStepStatus(EnumFlowStatus.APPROVE.code);
        return flowStep;
    }

    private FlowStep getFlowStep(long fid, String stepName, int stepNumber, Date date, String userid, String userName,int stepStatus) {
        FlowStep flowStep = new FlowStep();
        flowStep.setFlowId(fid);
        flowStep.setStepName(stepName);
        flowStep.setWaitUserId(userid);
        flowStep.setWaitUser(userName);
        flowStep.setApprovalOpinion("");
        flowStep.setCreateTime(date);
        flowStep.setStepNumber(stepNumber);
        flowStep.setStepStatus(stepStatus);
        return flowStep;
    }


    public enum EnumFlowStatus {
        DRAFT(0, "草稿"), WAIT(1, "待审核"), APPROVE(2, "审核通过"), REFUSE(3, "拒绝");
        private int code;
        private String msg;

        private EnumFlowStatus(int ordinal, String name) {
            this.code = ordinal;
            this.msg = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

}
