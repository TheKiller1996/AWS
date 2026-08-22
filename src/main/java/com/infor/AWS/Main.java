package com.infor.AWS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.infor.AWS")
public class Main {

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
}
