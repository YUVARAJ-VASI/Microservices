package com.task.apiGateWay.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class RouteConfig {

    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder) {

        return locatorBuilder.routes()

                .route("save-employee-route",
                        emp -> emp
                                .path("/employee/saveEmp")
                                .and()
                                .method(HttpMethod.POST)
                                .and()
                                .method(HttpMethod.PATCH,HttpMethod.GET,HttpMethod.DELETE)
                                .negate()
                                .uri("lb://employee-service")
                )

                .route("get-employee-route",
                        emp -> emp
                                .path("/employee/getAllEmp")
                                .or()
                                .path("/employee/getEmp/{id:[0-9]+}")
                                .and()
                                .method(HttpMethod.GET)
                                .uri("lb://employee-service")
                )

                .route("department-route",
                        dep -> dep.path("/department/**")
                                .uri("lb://department-service")
                )

                .build();
    }
}
