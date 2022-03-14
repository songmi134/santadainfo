package com.mountain.mountain.config.auth;


import com.google.firebase.auth.FirebaseAuth;
import com.mountain.mountain.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
public class AuthConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Bean
    @Profile("local")
    public AuthFilterContainer mockAuthFilter() {
        log.info("Initializing Mock AuthFilter");
        AuthFilterContainer authFilterContainer = new AuthFilterContainer();
        authFilterContainer.setAuthFilter(new MockAuthFilter(userService));
        return authFilterContainer;
    }

    @Bean
    @Profile("main")
    public AuthFilterContainer firebaseAuthFilter() {
        log.info("Initializing Firebase AuthFilter");
        AuthFilterContainer authFilterContainer = new AuthFilterContainer();
        authFilterContainer.setAuthFilter(new JwtFilter(userService, firebaseAuth));
        return authFilterContainer;
    }
}
