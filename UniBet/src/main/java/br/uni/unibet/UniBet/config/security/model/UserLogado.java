package br.uni.unibet.UniBet.config.security.model;

import br.uni.unibet.UniBet.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserLogado implements UserDetails {

    private Usuario user;

    public UserLogado(Usuario user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        roles.add( new SimpleGrantedAuthority("USER"));
        if (user.isEhAdmin()){
            roles.add( new SimpleGrantedAuthority("ADMIN"));
        }else{
            roles.add( new SimpleGrantedAuthority("APOSTADOR"));            
        }
        return roles;
    }

    public Usuario getUser(){
        return user;
    }
    
    @Override
    public String getPassword() {
        return user.getSenha();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
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
