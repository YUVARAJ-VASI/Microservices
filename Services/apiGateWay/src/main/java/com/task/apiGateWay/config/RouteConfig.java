package com.task.apiGateWay.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder){

        return locatorBuilder.routes()

                .route("employee-route",
                        emp -> emp.path("/employee/**")
                        .uri("lb://employee-service"))

                .route("department-route",
                        dep -> dep.path("/department/**")
                        .uri("lb://department-service"))

                .build();
    }
}
