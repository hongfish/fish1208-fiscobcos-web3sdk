package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FiscoBcosWeb3sdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiscoBcosWeb3sdkApplication.class, args);
    }

}
