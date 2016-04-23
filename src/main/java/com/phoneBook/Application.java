package com.phoneBook;

import com.phoneBook.models.Authorities;
import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import com.phoneBook.repository.ContactRepository;
import com.phoneBook.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Random;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {ContactRepository.class, UserRepository.class})
@EntityScan(basePackageClasses = {User.class, Contact.class, Authorities.class})
@EnableAutoConfiguration()
@Profile({"mysql","json"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

    //Бин для получения случайных картинок
    @Bean(name = "random")
    public Random random() {
        return new java.util.Random();
    }
}



