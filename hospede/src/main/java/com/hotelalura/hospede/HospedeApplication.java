package com.hotelalura.hospede;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HospedeApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospedeApplication.class, args);
    }
}
