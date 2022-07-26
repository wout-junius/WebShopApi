package com.ehb.webshopapi;

import com.ehb.webshopapi.models.Role;
import com.ehb.webshopapi.models.User;
import com.ehb.webshopapi.repositories.ProductRepository;
import com.ehb.webshopapi.repositories.SaleRepository;
import com.ehb.webshopapi.repositories.TagRepository;
import com.ehb.webshopapi.repositories.UserRepository;
import com.ehb.webshopapi.services.UserSevice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = { UserRepository.class, ProductRepository.class, SaleRepository.class, TagRepository.class})
public class WebShopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebShopApiApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserSevice userSevice) {
        if(userSevice.getusers().size() == 0) {
        return  args -> {
            userSevice.saveRole(new Role(null, "ROLE_USER"));
            userSevice.saveRole(new Role(null, "ROLE_ADMIN"));

            userSevice.saveUser(new User(null, "jhon", "jhon@fake.test" , "Test123!", new ArrayList<>()));
            userSevice.saveUser(new User(null, "mik", "mik@fake.test", "Test123!", new ArrayList<>()));

            userSevice.addRoleToUser("jhon", "ROLE_ADMIN");
            userSevice.addRoleToUser("mik", "ROLE_USER");
        };
        }
        return null;
    }

}
