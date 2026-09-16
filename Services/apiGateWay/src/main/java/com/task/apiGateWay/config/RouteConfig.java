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
                                .uri("lb://employee-service")
                )

                .route("save-external-employee-route",
                        emp -> emp
                                .path("/api/employee/saveEmp")
                                .and()
                                .method(HttpMethod.POST)
                                .filters(f ->
                                        f.stripPrefix(1)
                                                .addRequestHeader("X-Gateway-Source", "Gateway-8080")
                                                .addResponseHeader("X-App-Source", "Task", false)
                                )
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

                .route("get-path-chg-employee-route",
                        emp -> emp
                                .path("/api/emp/{id}/get")
                                .and()
                                .method(HttpMethod.GET)
                                .filters(f -> f
                                        .setPath("/employee/getEmp/{id}")
                                )
                                .uri("lb://employee-service")
                )

                .route("get-path-chg2-employee-route",
                        emp -> emp
                                .path("/api/employee/{id:[0-9]+}/get")
                                .and()
                                .method(HttpMethod.GET)
                                .filters(f -> f
                                        .rewritePath("/api/employee/(?<id>[0-9]+)/get", "/employee/getEmp/${id}")
                                        .addResponseHeader("X-App-Source", "Task")
                                )
                                .uri("lb://employee-service")
                )

                .route("department-route",
                        dep -> dep.path("/department/**")
                                .uri("lb://department-service")
                )

                .build();
    }
}
