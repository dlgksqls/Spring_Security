package io.security.springsecuritymaster;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider2 implements AuthenticationProvider {

    private final AuthenticationEventPublisher authenticationEventPublisher;

    public CustomAuthenticationProvider2(DefaultAuthenticationEventPublisher authenticationEventPublisher) {
        this.authenticationEventPublisher = authenticationEventPublisher;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(!authentication.getName().equals("user")) {
            authenticationEventPublisher.publishAuthenticationFailure(new BadCredentialsException("DisabledException"), authentication);

            throw new BadCredentialsException("BadCredentialsException");
        }
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
