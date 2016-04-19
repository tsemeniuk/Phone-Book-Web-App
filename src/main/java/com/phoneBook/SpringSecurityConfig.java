package com.phoneBook;

import com.phoneBook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = User.class)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login","/register").permitAll().antMatchers("/**").authenticated() /*access("hasRole('USER')")*/
                .and().formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .permitAll().and().logout().permitAll().and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);

        /*auth.inMemoryAuthentication()
                .withUser("user").password("123").roles("USER");*/
    }
}
