package com.phoneBook.Config;

import com.phoneBook.service.JsonUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Profile("json")
@Configuration
@PropertySource(value = "classpath:application-json.properties")
public class SpringSecurityConfigJson extends WebSecurityConfigurerAdapter {
    @Autowired
    JsonUserDetailsService jsonUserDetailsService;

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSourceJson() {
        DriverManagerDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        return dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/login", "/register").permitAll().antMatchers("/**").authenticated()
                .and().formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .permitAll().and().logout().permitAll().and().csrf().disable();
    }

    @Autowired
    public void configureGlobalJson(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jsonUserDetailsService);
    }
}