package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.responses.MyResponse;

@RestController
@RequestMapping("/api")
public class MyController {

    @Value("${app.name:Default Value}")
    private String appName;

    @GetMapping("/get")
    // @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getHello() {
        return "Hello from " + appName;
    }

    @GetMapping("/getMessage")
    public MyResponse getMessage() {
        return new MyResponse(1, appName + " is running AGAIN!!!", 200);
    }
}
