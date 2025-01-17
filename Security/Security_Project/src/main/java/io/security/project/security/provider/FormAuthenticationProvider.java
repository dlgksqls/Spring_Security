package io.security.project.security.provider;

import io.security.project.domain.dto.AccountContext;
import io.security.project.security.details.FormAuthenticationDetails;
import io.security.project.security.exception.SecretException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("authenticationProvider")
@RequiredArgsConstructor
public class FormAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String passworde = (String) authentication.getCredentials();

        AccountContext account = (AccountContext) userDetailsService.loadUserByUsername(loginId);

        if (!passwordEncoder.matches(passworde, account.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        String secretKey = ((FormAuthenticationDetails) authentication.getDetails()).getSecretKey();
        if (secretKey == null || !secretKey.equals("secret")){
            throw new SecretException("Invalid secret");
        }

        return new UsernamePasswordAuthenticationToken(account.getAccountDto(), null, account.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
