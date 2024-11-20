package com.example.provider;

import lombok.Data;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class PeopleProvider {

    private final WebClient webClient;

    public PeopleProvider(ReactorLoadBalancerExchangeFilterFunction loadbalancerExchangeFilterFunction) {
        this.webClient = WebClient.builder()
                .filter(loadbalancerExchangeFilterFunction)
                .build();
    }

    public List<Object> getProducts() {
        PersonResponse personResponse = webClient.get().uri("http://SHOP-SERVICE/api/shop")
                        .retrieve()
                        .bodyToMono(PersonResponse.class)
                        .block();

        return personResponse.getObj();
    }

    @Data
    private static class PersonResponse {
        private List<Object> obj;
    }

}