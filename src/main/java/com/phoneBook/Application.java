package com.phoneBook;

import com.phoneBook.models.Authorities;
import com.phoneBook.models.Contact;
import com.phoneBook.models.User;
import com.phoneBook.repository.ContactRepository;
import com.phoneBook.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
//@ComponentScan(basePackages = "com.phoneBook")
//@EnableJpaRepositories(basePackages = "repository", entityManagerFactoryRef = "emf")
//    @SQLInsert(sql = "classpath:db/data.sql")

@EnableJpaRepositories(basePackageClasses = {ContactRepository.class, UserRepository.class})
@EntityScan(basePackageClasses = {User.class, Contact.class, Authorities.class})
/*@EnableAutoConfiguration(exclude = {
        HibernateJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class})*/
public class Application extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/phonebook" /*+ "?verifyServerCertificate=false"
                + "&useSSL=false"
                + "&requireSSL=false"*/);
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root");
        return driverManagerDataSource;
    }

    /*@Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.phoneBook.repository");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }*/

}
