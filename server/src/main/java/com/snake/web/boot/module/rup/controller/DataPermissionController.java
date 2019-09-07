package com.snake.web.boot.module.rup.controller;

import com.snake.web.boot.module.rup.model.DataPermission;
import com.snake.web.boot.module.rup.repository.DataPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataPermission")
public class DataPermissionController {
    @Autowired
    private DataPermissionRepository dataPermissionRepository ;

    /**
     * 保存权限
     * @param dataPermission
     * @return
     */
    @RequestMapping(value = "save")
    Object save(@RequestBody DataPermission dataPermission ){
        dataPermissionRepository.save(dataPermission);
        return  dataPermission ;
    }

    /**
     * 获取权限
     * @param permissonType
     * @return
     */
    @RequestMapping(value = "getByPermissonType")
    Object getByPermissonType(String permissonType){
        DataPermission data = dataPermissionRepository.findByPermissionType(permissonType);
        if(data==null){
            data= new DataPermission();
            data.setPermissionType(permissonType);
        }
        return  data ;
    }

}
