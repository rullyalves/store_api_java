package com.store.user.impl;

import java.util.ArrayList;
import java.util.Collection;
/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
*/
public class User
/*
        implements UserDetails
*/
{

    private static final long serialVersionUID = -1254985166390931716L;

    private Long id;
    private String email;
    private String password;
   // private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
/*
    @Override
    public String getPassword() {
        return this.password;
    }
*/
    public void setPassword(String password) {
        this.password = password;
    }

    /*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    */
/*
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return email;
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

 */

}