package com.ehb.webshopapi.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class MyUserDetails implements UserDetails {

    Collection<? extends GrantedAuthority> authorities;
    String password;
    String username;

    public MyUserDetails(User user) {
        //this.authorities = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        this.password = user.getPassword();
        this.username = user.getUsername();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        System.out.print(this.password);
        return this.password;
    }

    @Override
    public String getUsername() {
        System.out.print(this.username);
        return this.username;
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
