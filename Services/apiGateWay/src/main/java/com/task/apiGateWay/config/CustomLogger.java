package com.task.apiGateWay.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jspecify.annotations.NullMarked;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

@Slf4j
@Component
public class CustomLogger implements GlobalFilter, Ordered {

    @Override
    @NullMarked
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String reqCorId = exchange.getRequest().getHeaders().getFirst("X-Correlation-Id");
        String correlationId = StringUtils.isBlank(reqCorId) ? UUID.randomUUID().toString() : reqCorId;


        Set<URI> originalUris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
        String originalUri = originalUris.isEmpty() ? exchange.getRequest().getURI().toString() : originalUris.iterator().next().toString();

        Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
        URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);

        if (route != null && routeUri != null) {
            log.info("Request: {} -> Route ID: '{}', Forwarding to: {}", originalUri, route.getId(), routeUri);
        } else {
            log.info("Request: {} -> No matching route found.", originalUri);
        }

        ServerWebExchange mutated = exchange.mutate().request(request -> request
                .header("X-Correlation-Id", correlationId)
                .build()
        ).build();

        return chain.filter(exchange.mutate().request(mutated.getRequest()).build());
    }

    @Override
    public int getOrder() {
        // Return a high precedence so it runs early
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
