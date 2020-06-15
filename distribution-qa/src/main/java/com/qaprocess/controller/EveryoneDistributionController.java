package com.qaprocess.controller;

import com.qaprocess.vo.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/everyone")
public class EveryoneDistributionController {

    @GetMapping("/qa/{id}")
    public ResponseResult getQA(@PathVariable("id") String id){
        return new ResponseResult();
    }

    @PostMapping("/qa/{id}")
    public ResponseResult setQA(@PathVariable("id") String id){
        return new ResponseResult();
    }

}
