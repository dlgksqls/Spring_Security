package io.secyruty.springsecuritymaster;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VIewController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
