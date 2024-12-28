package io.secyruty.springsecuritymaster;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/loginPage")
    public String login(){
        return "loginPage";
    }

    @GetMapping("/anonymous")
    public String anonymous(){
        return "anonymous";
    }

    @GetMapping("/authentication")
    public String authentication(Authentication authentication){ // 인증 받았다면 authentication에는 인증 받은 사용자 정보가 들어옴
        // 인증을 받지 못한다면 null이 들어옴
        if (authentication instanceof AnonymousAuthenticationToken){
            return "anonymous";
        }
        else{
            return "null";
        }
    }

    @GetMapping("/anonymousContext")
    public String anonymousContext(@CurrentSecurityContext SecurityContext context){
        return context.getAuthentication().getName();
    }
}
