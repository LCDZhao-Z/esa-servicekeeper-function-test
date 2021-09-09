package io.esastack.sailor.test;

import io.esastack.cabin.support.bootstrap.CabinAppBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        CabinAppBootstrap.run(args);
        SpringApplication.run(Application.class, args);
    }
}
