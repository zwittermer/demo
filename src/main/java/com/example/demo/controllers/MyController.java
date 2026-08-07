package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/get")
    // @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getHello() {
        return "Hello from GET !!!!!";
    }
}
