package com.micro.api;

import com.micro.api.external.IProductService;
import com.micro.api.external.Product;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class ApiClient implements IProductService {

    private final WebClient productClient;

    @Autowired
    public ApiClient(@Value("${consumer.api-client.product-url}") String productUrl,
                     @Value("${consumer.api-client.max-block-ms}") Integer maxBlockMs) {
        this.productClient = WebClient.builder()
                .baseUrl(productUrl)
                .clientConnector(new ReactorClientHttpConnector(timeoutConfig(maxBlockMs)))
                .build();
    }

    @Override
    public Product getProduct(Integer productNr) {
        return productClient.get().uri("/get/" + productNr).retrieve().bodyToMono(Product.class).block();
    }

    private HttpClient timeoutConfig(Integer maxMs) {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(maxMs, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(maxMs, TimeUnit.MILLISECONDS)));
    }
}
