package com.mountain.mountain.config.auth;

import org.springframework.web.filter.OncePerRequestFilter;

public class AuthFilterContainer {

    private OncePerRequestFilter authFilter;

    public void setAuthFilter(final OncePerRequestFilter authFilter) { this.authFilter = authFilter;}

    public OncePerRequestFilter getFilter() {return authFilter;}
}
