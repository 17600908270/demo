package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import server.webservice.dep.DEPServiceHttpBindingStub;

@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages  ={"server.webservice.dep","com"} )
public class DemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);

        System.out.println(  run.getBean("user"));
        DEPServiceHttpBindingStub dEPServiceHttpBindingStub = run.getBean(DEPServiceHttpBindingStub.class);

       // dEPServiceHttpBindingStub.setUser((com.example.demo.POJO.User) run.getBean("user"));
    }


   /* @Bean(value = "user")
    public User createUser(){

        return  new User();
    }*/
}
