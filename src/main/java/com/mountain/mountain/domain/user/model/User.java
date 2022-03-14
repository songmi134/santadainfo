package com.mountain.mountain.domain.user.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor
@Table(name ="user_info")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_img")
    private String userImg;

    @Column(name = "name")
    private String name;

    @Builder
    public User(String id,String userImg, String name) {
        this.id = id;
        this.userImg = userImg;
        this.name = name;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
