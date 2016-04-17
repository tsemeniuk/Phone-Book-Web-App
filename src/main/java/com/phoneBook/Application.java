package com.phoneBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

//@EnableJpaRepositories(basePackages = "com.phoneBook.repository")
@SpringBootApplication
//@EnableTransactionManagement
//@Component
//@Configuration
//@ComponentScan(basePackages = "com.phoneBook.entity")
//@EntityScan (basePackages = "com.phoneBook.entity")
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
