package io.security.project.security.provider;

import io.security.project.domain.dto.AccountContext;
import io.security.project.security.details.FormAuthenticationDetails;
import io.security.project.security.exception.SecretException;
import io.security.project.security.token.RestAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("restAuthenticationProvider")
@RequiredArgsConstructor
public class RestAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String password = (String) authentication.getCredentials();

        AccountContext account = (AccountContext) userDetailsService.loadUserByUsername(loginId);

        if (!passwordEncoder.matches(password, account.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

//        String secretKey = ((FormAuthenticationDetails) authentication.getDetails()).getSecretKey();
//        if (secretKey == null || !secretKey.equals("secret")){
//            throw new SecretException("Invalid secret");
//        }

        return new RestAuthenticationToken(account.getAuthorities(), account.getAccountDto(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
