package com.phoneBook.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable, Comparable<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Это поле обязательное.")
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Только английские символы, без спецсимволов : ()[]/\\|!@#$%^&*~+-_=")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "Это поле обязательное.")
    @Size(min = 5, max = 30)
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "Это поле обязательное.")
    @Size(min = 5, max = 30)
    @Column(name = "firstname")
    private String firstName;

    @NotEmpty(message = "Это поле обязательное.")
    @Size(min = 5, max = 30)
    @Column(name = "secondname")
    private String secondName;

    @NotEmpty(message = "Это поле обязательное.")
    @Size(min = 5, max = 30)
    @Column(name = "lastname")
    private String lastName;
    @Null
    private Boolean enabled;

    public User() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public User(@JsonProperty("username") String username,
                @JsonProperty("password") String password,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("secondName") String secondName,
                @JsonProperty("lastName") String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int compareTo(User user) {
        return id > user.id ? 1 : id == user.id ? 0 : -1;
    }


    @Override
    @JsonIgnore
    public Collection<GrantedAuthority> getAuthorities() {
        if (username.equals("admin")) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
