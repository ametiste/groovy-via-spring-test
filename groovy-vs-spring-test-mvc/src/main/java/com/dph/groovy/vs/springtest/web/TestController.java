package com.dph.groovy.vs.springtest.web;

import com.dph.groovy.vs.springtest.PrintStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Daria on 29.01.2015.
 */
@RestController
public class TestController {

    @Autowired
    @Qualifier("secondService")
    private PrintStringService service;

    @RequestMapping(value = "/")
    public String hello() {
        return "Hello";
    }

}
