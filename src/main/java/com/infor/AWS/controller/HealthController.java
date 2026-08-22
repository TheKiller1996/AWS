package com.infor.AWS.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class HealthController {

    @GetMapping("/status")
    public String statusCheck(){
        log.info("Logging from status checker");
        return "Ok";
    }
}
