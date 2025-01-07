package io.secyruty.springsecuritymaster;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
public class IndexController {
    @GetMapping("/")
    public Authentication index(Authentication authentication){
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

    @PostMapping("/csrf")
    public String csrf() {
        return "csrf 적용됨";
    }

    @GetMapping("/csrfToken")
    public String csrfToken(HttpServletRequest request){
        CsrfToken csrfToken1 = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        CsrfToken csrfToken2 = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        String token = csrfToken1.getToken();
        return token;
    }

    @PostMapping("/formCsrf")
    public CsrfToken formCsrf(CsrfToken csrfToken) {
        return csrfToken;
    }

    @PostMapping("/cookieCsrf")
    public CsrfToken cookieCsrf(CsrfToken csrfToken) {
        return csrfToken;
    }
}
