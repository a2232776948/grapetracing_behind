package cn.edu.scau.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String gender;
    private Date date;
    private int age;
    private String userface;
    private String detail;
    private String hash;
    private Boolean enabled;

    private List<Role> roles;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
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
        return enabled;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public void setUserface(String userface) {
        this.userface = userface == null ? null : userface.trim();
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public Date getDate() {
        return date;
    }

    public int getAge() {
        return age;
    }

    public String getUserface() {
        return userface;
    }

    public String getDetail() {
        return detail;
    }

    public String getHash() {
        return hash;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
