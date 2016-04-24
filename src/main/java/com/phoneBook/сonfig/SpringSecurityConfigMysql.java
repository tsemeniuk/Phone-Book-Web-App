package com.phoneBook.сonfig;

import com.phoneBook.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Profile("mysql")
@Configuration
@ComponentScan(basePackageClasses = User.class)
@PropertySource(value = "classpath:application-mysql.properties")
public class SpringSecurityConfigMysql extends WebSecurityConfigurerAdapter {
    @Autowired
    private Environment env;

    @Primary
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
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
    public void configureGlobalMysql(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource());
    }
}
