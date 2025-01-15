package io.security.springsecuritymaster;

import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IndexController {

    AuthenticationTrustResolverImpl trustResolver = new AuthenticationTrustResolverImpl();

    @GetMapping("/")
    public String index(){
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication(); // SpringSecurity 를 사용하여 인증 객체를 얻는 방법
        return trustResolver.isAnonymous(authentication) ? "anonymous" : "authenticated";
    }

    @GetMapping("/user1")
    public User user1(@AuthenticationPrincipal User user){
        return user;
    }

    @GetMapping("/user2")
    public String user2(@AuthenticationPrincipal(expression = "username") String user){
        return user;
    }

    @GetMapping("/currentUser1")
    public String currentUser1(@CurrentUser String user){
        return user;
    }

    @GetMapping("/currentUser2")
    public String currentUser2(@CurrentUsername String user){
        return user;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

}
