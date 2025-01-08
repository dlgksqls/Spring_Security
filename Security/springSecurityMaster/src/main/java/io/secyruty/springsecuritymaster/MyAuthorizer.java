package io.secyruty.springsecuritymaster;

import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.stereotype.Component;

@Component("myAuthorizer")
public class MyAuthorizer {

    public boolean isUser(MethodSecurityExpressionOperations root){
        boolean decision = root.hasAuthority("ROLE_USER");
        return decision;
    }
}
