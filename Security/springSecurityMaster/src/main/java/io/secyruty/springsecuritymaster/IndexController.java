package io.secyruty.springsecuritymaster;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
public class IndexController {
    @GetMapping("/")
    public Authentication index(Authentication authentication){
        return authentication;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @GetMapping("/db")
    public String db(){
        return "db";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

}
