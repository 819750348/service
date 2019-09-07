package com.snake.web.boot.module.interfaces.controller;

import com.snake.web.boot.module.interfaces.exception.NotFoundException;
import com.snake.web.boot.module.interfaces.mapper.ServiceRESTfulMapper;
import com.snake.web.boot.module.interfaces.model.RestfulBean;
import com.snake.web.boot.module.interfaces.service.ServiceRESTfulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.websocket.server.PathParam;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/v1/")
public class ServiceRESTfulController {
    @Autowired
    ServiceRESTfulService serviceRESTfulService;
    @Autowired
    ServiceRESTfulMapper serviceRESTfulMapper;


    /*
    *  返回jsp文件，目前无法返回，缺少依赖包
    *
    * */

    @RequestMapping("jsp")
    public ModelAndView  jsp(){
        ModelAndView modelAndView=new ModelAndView("ServiceRESTfulTest");
        return modelAndView;
    }

/*
* restfulController restful
* */
    @GetMapping(value = "string",produces = MediaType.TEXT_PLAIN_VALUE)
    public String string(){
        List<RestfulBean> list=serviceRESTfulService.allservice();
//        RestfulBean rb=list.get(0);
        return "list";
    }

    @GetMapping(value = "ob")
    public Object ob(){

        RestfulBean restfulBean=new RestfulBean();
        restfulBean.setName("123");
        return restfulBean;
    }

    @GetMapping(value = "list")
    public List list(){

        List<RestfulBean> list=serviceRESTfulService.allservice();
        return list;
    }

/*
*
*查询所有服务
* */

@GetMapping("service")
  public ResponseEntity allServices(){
      List<RestfulBean> list=serviceRESTfulService.allservice();
      if(list!=null) {
          throw new NotFoundException();
      }
      HttpHeaders httpHeaders=new HttpHeaders();
      httpHeaders.add("s", "su");
      httpHeaders.add("suc", "succ");
      ResponseEntity responseEntity=new ResponseEntity(list,httpHeaders,HttpStatus.CREATED);
      return responseEntity;
  }
//    @GetMapping("service")
//    @ResponseStatus(HttpStatus.OK)
//    public List allServices(){
//        List<RestfulBean> list=serviceRESTfulService.allservice();
//
//
//        return list;
//    }
/*
*
* 查询某个节点上的服务
* */
    @GetMapping(value = "services?nodeid={node_id}")
    public Object noteServices(@PathParam("nodeid")String nodeid){
        Example example =new Example(RestfulBean.class);
        example.createCriteria().andEqualTo("nodeid",nodeid);
        RestfulBean restfulBean=serviceRESTfulMapper.selectOneByExample(example);
        return restfulBean;
    }
    @PostMapping("services/register")
    public  Object registerService(String min_instance_count,String max_instance_count,String file_id){
        Example example =new Example(RestfulBean.class);
        example.createCriteria().andEqualTo("nodeid",file_id);
        RestfulBean restfulBean=serviceRESTfulMapper.selectOneByExample(example);
        return file_id;

     }
     @GetMapping("service/{service_id}")
    public Object oneService(@PathParam("service_id")String service_id){

         List<RestfulBean> list=serviceRESTfulService.allservice();
         HttpHeaders httpHeaders=new HttpHeaders();
         httpHeaders.add("success", "success");
         ResponseEntity responseEntity=new ResponseEntity(list,httpHeaders,HttpStatus.CREATED);
         return responseEntity;
     }

     @DeleteMapping("service/{service_id}")
     public Object delectService(@PathParam("service_id")String service_id){

        return null;
     }

}
