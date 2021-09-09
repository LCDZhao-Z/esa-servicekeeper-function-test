package io.esastack.sailor.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.setProperty("servicekeeper.config.dir", "./file-config-test/conf");
        System.setProperty("servicekeeper.config.name", "service-keeper.properties");
        SpringApplication.run(Application.class, args);
    }
}
