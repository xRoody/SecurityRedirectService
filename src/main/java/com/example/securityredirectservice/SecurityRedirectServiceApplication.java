package com.example.securityredirectservice;

import com.example.securityredirectservice.entities.Other;
import com.example.securityredirectservice.services.OtherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SecurityRedirectServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(SecurityRedirectServiceApplication.class, args);
        /*OtherService service=context.getBean(OtherService.class);
        service.save(new Other("AdminService","Sup3r+S3cret-p4ssw0rd","ADMIN"));
        service.save(new Other("KitchenService","Sup3r+S3cret-p4ssw0rd","KITCHEN"));
        service.save(new Other("UserService","Sup3r+S3cret-p4ssw0rd","USER"));*/
    }
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "offer_service")
    public WebClient webClient1(){
        return WebClient.create("http://localhost:8080");
    }

    @Bean(name = "orders")
    public WebClient webClient2(){
        return WebClient.create("http://localhost:8082");
    }

}
