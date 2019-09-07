package com.snake.web.boot.module.rup.controller;

import com.snake.web.boot.module.system.model.UserRole;
import com.snake.web.boot.module.system.repository.UserRoleRepository;
import net.sf.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snake.web.boot.config.ApiResult;
import com.snake.web.boot.config.ApiResultType;
import com.snake.web.boot.module.rup.mapper.BaseLabelMapper;
import com.snake.web.boot.module.rup.mapper.BusinessUserMapper;
import com.snake.web.boot.module.rup.model.BaseLabel;
import com.snake.web.boot.module.rup.repository.BusinessUserRepository;
import com.snake.web.boot.module.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/businessuser/")
public class BusinessUserController {

    @Autowired
    private BusinessUserRepository businessUserRepository;
    @Autowired
    private BusinessUserMapper businessUserMapper;

    @Autowired
    private BaseLabelMapper baseLabelMapper ;

    @Autowired
    private UserRoleRepository userRoleRepository ;


    /**
     * 通过用户id获取用户
     * @param id
     * @return
     */
    @RequestMapping(value = "getUserById")
    Object getUserById(@RequestParam(name = "id")
                             Long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        return user;
    }

    /**
     * 获取待审核的用户
     * @return
     */
    @RequestMapping(value = "getNotEnabledUsers")
    Object getNotEnabledUsers(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                      int pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                      int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("enabled", 0);
        example.setOrderByClause("id desc"); //排序
        List<User> users = businessUserMapper.selectByExample(example);
        return new PageInfo(users);
    }



    /**
     * 注册
     * @param businessUser
     * @return
     */
    @RequestMapping(value = "register")
    Object register(@RequestBody User businessUser) {
        User user = businessUserRepository.findByUsername(businessUser.getUsername());
        if (user != null) {
            return   ApiResult.error(ApiResultType.BUSINESS_ERROR, "登录名已经存在");
        }
        businessUser.setEnabled(false);//设置默认值
        businessUser.setIsLeader(false);//设置默认值
        businessUser.setIsEvaluator(false);//设置默认值
        businessUser.setIsTester(false);//设置默认值
        businessUser.setIsAdmin(false);//设置默认值


        //设置测试默认值
        businessUser.setTestEvaluation(false);
        businessUser.setTestEquipment(false);
        businessUser.setTestSupport(false);
        businessUser.setTestEnvironment(false);
        businessUser.setTestCambot(false);

        //设置评估人员
        businessUser.setEvaluateSupport(false);
        businessUser.setEvaluateEvaluation(false);
        businessUser.setEvaluateEquipment(false);
        businessUser.setEvaluateEnvironment(false);
        businessUser.setEvaluateCambot(false);

        businessUserRepository.save(businessUser);
        UserRole userRole = new UserRole();
        userRole.setCreateTime(new Date());
        userRole.setRoleId(3l);
        userRole.setUserId(businessUser.getId());
        userRoleRepository.save(userRole);//设置用户角色
        return ApiResultType.SUCCESS;
    }

    /**
     * 更新用户信息
     * @return
     */
    @RequestMapping(value = "updateUser")
    Object updateUser(@RequestBody User businessUser) {
        businessUserMapper.updateByPrimaryKey(businessUser);
        return ApiResultType.SUCCESS;
    }


    /**
     * 设置为领导
     * @param id
     * @return
     */
    @RequestMapping(value = "setLeader")
    Object setLeader(@RequestParam(name = "id")
                             long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setIsLeader(true);
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }


    /**
     * 取消设置领导
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelLeader")
    Object cancelLeader(@RequestParam(name = "id")
                                long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setIsLeader(false);
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }

    /**
     * 设置为管理员
     * @param id
     * @return
     */
    @RequestMapping(value = "setAdmin")
    Object setAdmin(@RequestParam(name = "id")
                             long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setIsAdmin(true);
        List<UserRole> list = userRoleRepository.findByUserId(user.getId());
        for(UserRole userRole: list){
            userRole.setRoleId(2l);
            userRoleRepository.save(userRole);
        }
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }


    /**
     * 取消管理员
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelAdmin")
    Object cancelAdmin(@RequestParam(name = "id")
                                long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setIsAdmin(false);
        List<UserRole> list = userRoleRepository.findByUserId(user.getId());
        for(UserRole userRole: list){
            userRole.setRoleId(3l);
            userRoleRepository.save(userRole);
        }
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }


    /**
     * 设置为测试人员
     * @param id
     * @return
     */
    @RequestMapping(value = "setTester")
    Object setTester(@RequestParam(name = "id")
                            long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setIsTester(true);
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }


    /**
     * 取消测试人员
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelTester")
    Object cancelTester(@RequestParam(name = "id")
                               long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setIsTester(false);
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }


    /**
     * 设置为评估人员
     * @param id
     * @return
     */
    @RequestMapping(value = "setEvaluator")
    Object setEvaluator(@RequestParam(name = "id")
                             long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setIsEvaluator(true);
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }

    /**
     * 取消评估人员
     * @param id
     * @return
     */
    @RequestMapping(value = "cancelEvaluator")
    Object cancelEvaluator(@RequestParam(name = "id")
                                long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setIsEvaluator(false);
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }

    /**
     * 设置标签
     * @return
     */
    @RequestMapping(value = "setLabel")
    Object setLabel(@RequestBody User businessUser) {
        User user = businessUserMapper.selectByPrimaryKey(businessUser.getId());
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setLabel(businessUser.getLabel());
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }


    /**
     * * 设置备注
     * @param businessUser
     * @return
     */
    @RequestMapping(value = "setRemark")
    Object setRemark(@RequestBody User businessUser) {
        User user = businessUserMapper.selectByPrimaryKey(businessUser.getId());
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setRemark(businessUser.getRemark());
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }

    /**
     * * 设置部门
     * @param businessUser
     * @return
     */
    @RequestMapping(value = "setDepartment")
    Object setDepartment(@RequestBody User businessUser) {
        User user = businessUserMapper.selectByPrimaryKey(businessUser.getId());
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setDepartment(businessUser.getDepartment());
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }

    /**
     * 审核通过
     * @param id
     * @return
     */
    @RequestMapping(value = "approve")
    Object approve(@RequestParam(name = "id")
                           long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setEnabled(true);
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }

    /**
     * 拉入黑名单
     * @param id
     * @return
     */
    @RequestMapping(value = "refuse")
    Object refuse(@RequestParam(name = "id")
                          long id) {
        User user = businessUserMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setEnabled(false);
        businessUserMapper.updateByPrimaryKey(user);
        return ApiResultType.SUCCESS;
    }


    /**
     * 获取标签及数据条数
     * @param label
     * @return
     */
    @RequestMapping(value = "getlabels")
    Object getLabels(@RequestParam(name = "label", required = false, defaultValue = "")
                             String label) {
        List<BaseLabel> list = baseLabelMapper.selectLabels();
        return list;
    }

    /**
     * 获取标签用户
     *
     * @param pageNum
     * @param pageSize
     */
    @RequestMapping(value = "getLabelUsers")
    Object getLabelUsers(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                 int pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                                 int pageSize, @RequestBody JSONObject param) {
//        jsonObject,
        PageHelper.startPage(pageNum, pageSize);

        String keys = "" ;
        if(param.containsKey("keys")){
            keys = param.getString("keys");
        }
        String label = "";
        if(param.containsKey("label")){
            label = param.getString("label") ;
        }
        List<User> users = businessUserMapper.selectLabelUsers(label,keys);
        return new PageInfo(users);
    }


    /**
     * 获取admin用户
     */
    @RequestMapping(value = "getAdmins")
    Object getAdmins() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("isAdmin", 1);
        example.setOrderByClause("id desc"); //排序
        List<User> users = businessUserMapper.selectByExample(example);
        return users;
    }

    /**
     * 获测试用户
     */
    @RequestMapping(value = "getTesters")
    Object getTesters() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("isTester", 1);
        example.setOrderByClause("id desc"); //排序
        List<User> users = businessUserMapper.selectByExample(example);
        return users;
    }

    /**
     * 获取评估用户
     */
    @RequestMapping(value = "getEvaluators")
    Object getEvaluators() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("isEvaluator", 1);
        example.setOrderByClause("id desc"); //排序
        List<User> users = businessUserMapper.selectByExample(example);
        return users;
    }


    /**
     * 获取领导们
     */
    @RequestMapping(value = "getLeaders")
    Object getLeaders() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("isLeader", 1);
        example.setOrderByClause("id desc"); //排序
        List<User> users = businessUserMapper.selectByExample(example);
        return users;
    }

    /**
     * 获取关键用户
     *
     * @param pageNum
     * @param pageSize
     * @param keys
     */
    @RequestMapping(value = "getKeysUsers")
        Object getKeysUsers(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
        int pageNum,
        @RequestParam(name = "pageSize", required = false, defaultValue = "20")
        int pageSize,
        @RequestParam(name = "keys", required = false, defaultValue = "")
        String keys) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = businessUserMapper.selectKeysUsers(keys);
        return new PageInfo(users);
    }


    /**
     * * 设置测试范围
     * @param
     * @return
     */
    @RequestMapping(value = "setTestRange")
    Object setTestRange(@RequestBody User businessUser) {
        User user = businessUserMapper.selectByPrimaryKey(businessUser.getId());
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setTestCambot(businessUser.getTestCambot());
        user.setTestEnvironment(businessUser.getTestEnvironment());
        user.setTestEquipment(businessUser.getTestEquipment());
        user.setTestSupport(businessUser.getTestSupport());
        user.setTestEvaluation(businessUser.getTestEvaluation());
        businessUserMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    /**
     * * 设置评估范围
     * @param
     * @return
     */
    @RequestMapping(value = "setEvaluationRange")
    Object setEvaluationRange(@RequestBody User businessUser) {
        User user = businessUserMapper.selectByPrimaryKey(businessUser.getId());
        if (user == null) {
            return ApiResult.error(ApiResultType.BUSINESS_ERROR, "用户不存在");
        }
        user.setEvaluateCambot(businessUser.getEvaluateCambot());
        user.setEvaluateEnvironment(businessUser.getEvaluateEnvironment());
        user.setEvaluateEquipment(businessUser.getEvaluateEquipment());
        user.setEvaluateEvaluation(businessUser.getEvaluateEvaluation());
        user.setEvaluateSupport(businessUser.getEvaluateSupport());
        businessUserMapper.updateByPrimaryKeySelective(user);
        return user;
    }

}
