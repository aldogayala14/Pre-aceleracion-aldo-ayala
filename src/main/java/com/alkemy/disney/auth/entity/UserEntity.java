package com.alkemy.disney.auth.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Email
        private String username;
        @Size(min = 8)
        private String password;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;

        public UserEntity(){
            this.accountNonExpired = true;
            this.accountNonLocked = true;
            this.credentialsNonExpired = true;
            this.enabled = true;
        }


    public Long getId() { return id;  }

    public void setId(Long id) {  this.id = id;   }

    public void setUsername(String username){
            this.username = username;
    }

    public void setPassword(String password){
            this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return  this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return  this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
