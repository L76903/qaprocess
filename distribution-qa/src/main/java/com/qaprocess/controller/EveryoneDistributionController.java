package com.qaprocess.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/everyone")
public class EveryoneDistributionController {

    @GetMapping("/qa/{id}")
    public Object getQA(@PathVariable("id") String id){
        return new Object();
    }

    @PostMapping("/qa/{id}")
    public Object setQA(@PathVariable("id") String id){
        return new Object();
    }

}
