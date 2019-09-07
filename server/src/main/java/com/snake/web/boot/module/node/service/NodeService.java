package com.snake.web.boot.module.node.service;

import com.snake.web.boot.module.node.mapper.NodeMapper;
import com.snake.web.boot.module.node.model.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService {
    @Autowired
    NodeMapper nodeMapper;
    public List<NodeInfo> nodes1(){
        List<NodeInfo> list=nodeMapper.selectAll();
        return list;
    }
    public List<NodeInfo> selectKeyword1(String keyword){
        return nodeMapper.selectKeyword1(keyword);
    }

    public List<NodeInfo> changePage1(String start,String pageSize,String keys){

        return nodeMapper.changePage1(String.valueOf((Integer.parseInt(start)-1)*Integer.parseInt(pageSize)),pageSize,keys);
    }
}
