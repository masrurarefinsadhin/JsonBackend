package com.jsonbook.Json.Book.entity;

import com.jsonbook.Json.Book.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

public class CustomUserDetails implements UserDetails {
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        //this.authorities= new SimpleGrantedAuthority("ADMIN");
        this.authorities = new ArrayList<>();
        for(Roles r: user.getRoleAccess()){authorities.add(new SimpleGrantedAuthority(r.getRoleType().toString()));}
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return (Collection<? extends GrantedAuthority>) authorities;
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
