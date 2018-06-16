package io.sandstorm.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = {"io.sandstorm"})
@ServletComponentScan
@ImportResource("classpath*:spring/*.xml")
public class RestServer {
    public static void main(String[] args) {
        SpringApplication.run(RestServer.class, args);
    }
}

