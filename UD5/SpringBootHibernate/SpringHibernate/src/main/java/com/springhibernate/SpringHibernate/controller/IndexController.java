package com.springhibernate.SpringHibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    @ResponseBody
    public String showIndex() {
        return "Hello From Spring Boot!";
    }

    @GetMapping("/hello")
    @ResponseBody

    public String showHello() {
        return "Hello World!";
    }

    @GetMapping("/hello/goodbye")
    @ResponseBody
    public String showGoodbye() {
        return "Goodbye World!";
    }

    @GetMapping("/hello/{name}/hello")
    @ResponseBody
    public String showHelloName(@PathVariable String name) {
        return "Hello " + name + "!";
    }

}
