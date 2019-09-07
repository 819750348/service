package com.snake.web.boot.module.rup.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snake.web.boot.config.ApiResultType;
import com.snake.web.boot.module.rup.mapper.BaseLabelMapper;
import com.snake.web.boot.module.rup.model.BaseLabel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@RestController
@RequestMapping("/base/")
public class BaseLabelController {

    @Autowired
    private BaseLabelMapper baseLabelMapper ;


    /**
     * 获取标签
     * @param label
     * @return
     */
    @RequestMapping(value = "getBaseLabels")
    Object getBaseLabels(@RequestParam(name = "pageNum", required = false, defaultValue = "1")
                             int pageNum,
                     @RequestParam(name = "pageSize", required = false, defaultValue = "20")
                             int pageSize,
                     @RequestParam(name = "label",required = false, defaultValue ="" )
                             String label) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(BaseLabel.class);
        if(!StringUtils.isEmpty(label)){
            example.createCriteria().andEqualTo("label", label);
        }
        example.setOrderByClause("id desc"); //排序
        List<BaseLabel> list = baseLabelMapper.selectByExample(example);
        return new PageInfo(list);
    }



    /**
     * 新增标签
     * @param baseLabel
     * @return
     */
    @RequestMapping(value = "addBaseLabel")
    Object addBaseLabel(@RequestBody  BaseLabel baseLabel) {
        baseLabelMapper.insert(baseLabel) ;
        return baseLabel;
    }


    /**
     * 删除标签
     * @return
     */
    @RequestMapping(value = "delBaseLabel")
    Object delBaseLabel(@RequestParam(name = "id")
                                 long id) {
        baseLabelMapper.deleteByPrimaryKey(id);
        return ApiResultType.SUCCESS;
    }


    /**
     * 修改标签名称
     * @param baseLabel
     * @return
     */
    @RequestMapping(value = "updateBaseLabel")
    Object updateBaseLabel(@RequestBody  BaseLabel baseLabel) {
        baseLabelMapper.updateByPrimaryKeySelective(baseLabel);
        return ApiResultType.SUCCESS;
    }

}
