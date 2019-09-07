package com.snake.web.boot.module.node.controller;

import com.snake.web.boot.module.node.model.NodeInfo;
import com.snake.web.boot.module.node.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/")
public class NodeController {
    @Autowired
    NodeService nodeService;
    @GetMapping("nodes1")
    public List<NodeInfo> nodes1(){
        List<NodeInfo> list=nodeService.nodes1();
        return list;
    }

    @ResponseBody
    @RequestMapping("selectKeyword1")
    public List<NodeInfo> selectKeyword1(String keyword){

        List<NodeInfo> list=nodeService.selectKeyword1(keyword);
        return list;
    }

    @ResponseBody
    @RequestMapping("changePage1")
    public List<NodeInfo> changePage1(String start,String pageSize,String keys){

        List<NodeInfo> list=nodeService.changePage1(start,pageSize,keys);
        return list;

    }

}
