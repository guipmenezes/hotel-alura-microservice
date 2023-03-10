package com.hotelalura.hospede;

import io.micrometer.observation.annotation.Observed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@Observed
public class HospedeApplication {
    public static void main(String[] args) {
        SpringApplication.run(HospedeApplication.class, args);
    }
}
