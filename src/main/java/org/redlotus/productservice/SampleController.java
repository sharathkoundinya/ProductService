package org.redlotus.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//This controller supports REST APIs (HTTP requests)
@RestController
// The requests coming to endpoint /hello transfer them to this  controller
@RequestMapping("/hello")
public class SampleController {
    @GetMapping("/name={name}&num={number}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("number") int number) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<number; i++) {
            sb.append("Hello " + name).append("\n") ;
        }
        return sb.toString();
    }



    //    @GetMapping("/name={name}&loc={location}")
//    public String sayHello(@PathVariable("name") String name, @PathVariable("location") String location) {
//        return "Hello " +  name + " from " + location +", this is my first API!";
//    }
}
