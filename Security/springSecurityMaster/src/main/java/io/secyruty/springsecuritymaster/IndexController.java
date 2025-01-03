package io.secyruty.springsecuritymaster;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IndexController {
    @GetMapping("/")
    public Authentication index(Authentication authentication, HttpSession session){

        log.info("Session = {}", session.getId());
        return authentication;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/loginPage")
    public String login(){
        return "loginPage";
    }

}
