package com.scaler.demo123;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello" , method = RequestMethod.GET)
    public String sayHello()
    {
        return "Hello this is new";
    }

    @RequestMapping(value = "/hello/{id}" , method = RequestMethod.GET)
    public String sayHello1(@PathVariable("id") String id)
    {
        return "Hello this is new" + id;
    }
}
