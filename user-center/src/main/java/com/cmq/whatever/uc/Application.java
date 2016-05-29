package com.cmq.whatever.uc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by admin on 16/5/29.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        ApplicationContext ctx = SpringApplication.run(Application.class,args);
        System.out.println("Application started...................");

    }
}
