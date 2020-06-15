package com.qaprocess.controller;

import com.qaprocess.vo.ResponseResult;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/admin")
public class AdminDistributionController {
    /**
     *获取问卷的全部信息
     * @param id 试卷ID
     * @return
     */
    @GetMapping(value = "/qa/{id}")
    public ResponseResult getQA(@PathVariable("id") String id){
        return new ResponseResult();
    }

    /**
     * 修改问卷
     * @param id 问卷ID
     * @return
     */
    @PostMapping(value = "/qa/{id}")
    public ResponseResult setQA(@PathVariable("id") String id){
        return new ResponseResult();
    }

    /**
     * 添加问卷
     * @return
     */
    @PostMapping(value = "/qa")
    public ResponseResult addQA(){
        return new ResponseResult();
    }

    /**
     *启动或删除问卷
     * @param id 试卷ID
     * @param status 0为启用，1为删除
     * @return
     */
    @GetMapping(value = "/qa/{id}")
    public ResponseResult delQA(@PathVariable("id") String id,@RequestParam(value = "status",required = true) int status){
        return new ResponseResult();

    }

}
