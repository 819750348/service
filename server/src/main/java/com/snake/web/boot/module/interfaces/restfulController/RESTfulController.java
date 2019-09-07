package com.snake.web.boot.module.interfaces.restfulController;

import com.snake.web.boot.module.node.model.NodeInfo;
import com.snake.web.boot.module.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@ResponseBody
@RequestMapping("/v1/")
public class RESTfulController {
    @Autowired
    ServiceService serviceService;
    @RequestMapping("test")
    public Object n() {
        RestTemplate restTemplate=new RestTemplate();
//        ResponseEntity<RestfulBean> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/all/service",RestfulBean.class);
//        RestfulBean restfulBean = restTemplate.getForObject("http://127.0.0.1:8080/all/service",RestfulBean.class);
//        String restfulString = restTemplate.getForObject("http://127.0.0.1:8080/v1/string",String.class);
//        Object restfulOb = restTemplate.getForObject("http://127.0.0.1:8080/v1/ob",Object.class);
//        Object restfullist = restTemplate.getForObject("http://127.0.0.1:8080/v1/list",Object.class);
//
//
//
//
//        Object restfulservice = restTemplate.getForObject("http://127.0.0.1:8080/v1/service",Object.class);
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/v1/service",Object.class);
        return  responseEntity;

    }


   /*
   * 查询所有节点
   * */

    @RequestMapping("service")
    public Object service(){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/v1/service1",Object.class);
        return responseEntity;
    }
    @RequestMapping("nodes")
    public Object nodes(String start,String pageSize){
        RestTemplate restTemplate=new RestTemplate();
        String url="http://127.0.0.1:8080/v1/nodes1";
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url,Object.class);
        return responseEntity;
    }
    //   服务浏览页面根据节点编号、用户名、IP进行检索
    @ResponseBody
    @RequestMapping("selectKeyword")
    public Object selectKeyword( String keyword){
        RestTemplate restTemplate=new RestTemplate();
//        传递参数要在url中给占位符
        String url ="http://127.0.0.1:8080/v1/selectKeyword1?keyword={keyword}";
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url,Object.class,keyword);
        return responseEntity;
    }

//    服务节点页面分页查询
    @ResponseBody
    @RequestMapping("changePage")
    public Object changePage(String start,String pageSize,String keys){
        RestTemplate restTemplate= new RestTemplate();
        String url ="http://127.0.0.1:8080/v1/changePage1?start="+start+"&pageSize="+pageSize+"&keys="+keys;
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url,Object.class,start,pageSize);
        return responseEntity;
    }


    //    服务浏览页面分页查询
    @ResponseBody
    @RequestMapping("currentChangeService")
    public Object currentChangeService(String start,String pageSize,String keys){
        RestTemplate restTemplate= new RestTemplate();
        String url ="http://127.0.0.1:8080/v1/currentChangeService1?start="+start+"&pageSize="+pageSize+"&keys="+keys;
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url,Object.class,start,pageSize);
        return responseEntity;
    }
}

