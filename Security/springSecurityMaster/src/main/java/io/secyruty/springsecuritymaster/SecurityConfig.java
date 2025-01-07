package io.secyruty.springsecuritymaster;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SpaCsrfTokenRequestHandler csrfTokenRequestHandler = new SpaCsrfTokenRequestHandler();

        http
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/csrf", "/csrfToken", "/form", "/formCsrf", "/cookieCsrf", "/cookie").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(csrfTokenRequestHandler)
                )
                .addFilterBefore(new CsrfCookieFilter(), BasicAuthenticationFilter.class) // CsrfCookieFilter를 BasicAuthenticationFilter전에 추가하겠다
                ;

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository(); // default 는 세션에 csrf 를 저장함
//        XorCsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new XorCsrfTokenRequestAttributeHandler();
//        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName(null); // null 로 하면 즉시로딩, default 는 지연 로딩(이걸 추천)
//
//        http
//                .authorizeHttpRequests( auth -> auth
//                        .requestMatchers("/csrf", "/csrfToken", "/form", "/formCsrf").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(Customizer.withDefaults())
////                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
////                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler))
//                ;
//
//        return http.build();
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        return  new InMemoryUserDetailsManager(user);
    }

}
