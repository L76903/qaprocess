package com.qaprocess.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class AdminDistributionController {


    /**
     * 修改问卷
     * @param id 问卷ID
     * @return
     */
    @PostMapping(value = "/qa/{id}")
    public Object setQA(@PathVariable("id") String id){
        return new Object();
    }

    /**
     * 添加问卷
     * @return
     */
    @PostMapping(value = "/qa")
    public Object addQA(){
        return new Object();
    }

    /**
     *启动或删除问卷,获取问卷的全部信息
     * @param id 试卷ID
     * @param status 0为启用，1为删除
     * @return
     */
    @GetMapping(value = "/qa/{id}")
    public Object delQA(@PathVariable("id") String id
            ,@RequestParam(value = "status",required = false,defaultValue = "0") int status){
        return new Object();
    }

}
