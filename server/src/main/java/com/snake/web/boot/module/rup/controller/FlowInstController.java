package com.snake.web.boot.module.rup.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snake.web.boot.module.controller.BasicController;
import com.snake.web.boot.module.rup.model.FlowInst;
import com.snake.web.boot.module.rup.model.FlowStep;
import com.snake.web.boot.module.rup.model.ModelInfo;
import com.snake.web.boot.module.rup.service.FlowInstService;
import io.swagger.annotations.OAuth2Definition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InterfaceAddress;
import java.util.List;

@RestController
@RequestMapping("/flow/")
public class FlowInstController  extends BasicController {
    @Autowired
    private FlowInstService flowInstService;

    @RequestMapping(value = "getFlowStep")
    Object getFlowStep(Long id, Integer flowType) {
        return flowInstService.getFlowStep(id,flowType);
    }



    @RequestMapping(value = "approveFlow")
    Object approveFlow(@RequestBody FlowStep flowStep) {
        return flowInstService.approveFlow(flowStep);
    }

    @RequestMapping(value = "refuseFlow")
    Object refuseFlow(@RequestBody FlowStep flowStep) {
        return flowInstService.refuseFlow(flowStep);
    }


    /**
     * 我的任务
     * @return
     */
    @RequestMapping(value = "myFlows")
    Object myFlows(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                           int pageNum,
                   @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                           int pageSize,
                   @RequestParam(name = "type", required = false, defaultValue = "0")
                           int type){
        PageHelper.startPage(pageNum, pageSize);
        List<FlowInst> list = flowInstService.getFlows(loginUserId(),type);
        return new PageInfo(list);
    }


    /**
     * 我的申请
     * @return
     */
    @RequestMapping(value = "myApplies")
    Object myApplies(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                           int pageNum,
                   @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                           int pageSize,
                   @RequestParam(name = "type", required = false, defaultValue = "1")
                           int type){
        PageHelper.startPage(pageNum, pageSize);
        List<FlowInst> list = flowInstService.getApplies(loginUserId(),type);
        return new PageInfo(list);
    }


}
