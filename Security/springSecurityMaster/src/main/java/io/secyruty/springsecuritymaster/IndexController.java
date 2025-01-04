package io.secyruty.springsecuritymaster;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public Authentication index(Authentication authentication){
        throw new AuthenticationServiceException("error"); // 인증 예외
//        return authentication;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/login")
    public String login(){
        return "LoginPage";
    }

    @GetMapping("/denied")
    public String denied(){
        return "denied";
    }

}
