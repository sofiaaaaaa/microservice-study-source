package com.example.secondservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
public class SecondServiceController {
    @GetMapping("welcome")
    public String welcome(){
        return "welcome 2";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header){
        return header;
    }

    @GetMapping("/check")
    public String check(){
        return "Check from second-service";
    }
}
