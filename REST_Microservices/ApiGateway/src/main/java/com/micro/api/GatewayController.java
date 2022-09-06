package com.micro.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GatewayController {

    private final String aboUrl;
    private final String cartUrl;
    private final String paymentUrl;
    private final String productUrl;

    public GatewayController(@Value("${gateway.api.abo-url}") String aboUrl,
                             @Value("${gateway.api.cart-url}") String cartUrl,
                             @Value("${gateway.api.payment-url}") String paymentUrl,
                             @Value("${gateway.api.product-url}") String productUrl) {
        this.aboUrl = aboUrl;
        this.cartUrl = cartUrl;
        this.paymentUrl = paymentUrl;
        this.productUrl = productUrl;
    }

    @RequestMapping("/fallback")
    public String fallback() {
        return "This is a fallback.";
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        //@formatter:off
        return builder.routes()
                .route("abo_route", r -> r
                        .path("/abo/**")
                        .uri(aboUrl))
                .route("abo_route_base", r -> r
                        .path("/abo")
                        .uri(aboUrl))
                .route("cart_route", r -> r
                        .path("/cart/**")
                        .uri(cartUrl))
                .route("cart_route_base", r -> r
                        .path("/cart")
                        .uri(cartUrl))
                .route("payment_route", r -> r
                        .path("/payment/**")
                        .uri(paymentUrl))
                .route("payment_route_base", r -> r
                        .path("/payment")
                        .uri(paymentUrl))
                .route("product_route", r -> r
                        .path("/product/**")
                        .uri(productUrl))
                .route("product_route_base", r -> r
                        .path("/product")
                        .uri(productUrl))
                .build();
    }

    // TODO user authentication https://github.com/spring-cloud-samples/spring-cloud-gateway-sample/blob/main/src/main/java/com/example/demogateway/DemogatewayApplication.java
}
