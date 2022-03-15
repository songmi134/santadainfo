package com.mountain.mountain.config;

import com.mountain.mountain.config.auth.AuthFilterContainer;
import com.mountain.mountain.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    private AuthFilterContainer authFilterContainer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // CSRF 보호기능 disable
                .authorizeRequests() // 요청에 대한 권한 지정
                .anyRequest().authenticated() // 모든 요청이 인증되어야한다.
                .and()
                .addFilterBefore(authFilterContainer.getFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        //인증 예외 URL 설정

        web.ignoring().antMatchers(HttpMethod.POST, "/users")
                .antMatchers("/")
                .antMatchers("/login")
                .antMatchers("/mountain/**")
                .antMatchers("/community/**")
                .antMatchers("/my")
                .antMatchers(HttpMethod.GET ,"/mountains")
                .antMatchers(HttpMethod.GET ,"/mountains/**")
                .antMatchers(HttpMethod.GET, "/categories")
                .antMatchers(HttpMethod.GET, "/categories/**")
                .antMatchers(HttpMethod.GET, "/communities")
                .antMatchers(HttpMethod.GET, "/communities/**")
                .antMatchers("/css/**")
                .antMatchers("/static/**")
                .antMatchers("/js/**")
                .antMatchers("/img/**")
                .antMatchers("/fonts/**")
                .antMatchers("/vendor/**")
                .antMatchers("/favicon.ico")
                .antMatchers("/pages/**")
        ;
    }

}