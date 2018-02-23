package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        String samtykke = "http://localhost:4178";

        return builder.routes()
                .route("samtykke", r -> r
                        .path("/gb-integration/sbl/**")
                        .filters(f -> f.rewritePath("/gb-integration/sbl/(?<segment>.*)", "/gb-samtykke-integration/${segment}"))
                        .uri(samtykke))
                .build();
    }
}
