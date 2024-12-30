package io.secyruty.springsecuritymaster;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IndexController {

//    @GetMapping("/")
//    public String index() {
//        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        log.info("authentication = {}", authentication);
//
//        return "index";
//    }

    @GetMapping("/")
    public Authentication index(Authentication authentication) {

        return authentication;
    }

    @GetMapping("/loginPage")
    public String login(){
        return "loginPage";
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess(){
        return "logoutSuccess";
    }
}
