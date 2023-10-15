package com.example.foodbook.controllers;

import com.example.foodbook.dto.RecipeAPIDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class TestWebClientController {
    private final String API_KEY="3345d443c0e4442c8060dee679aa8c53";
    private final String REQUEST="https://api.spoonacular.com/recipes/complexSearch?instructionsRequired=true";
    @GetMapping("/testWebClient")
    public Mono<RecipeAPIDTO> testWebClient() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();
       /* WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/recipes/665540");
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue("data");
        Mono<RecipeAPIDTO> response = headersSpec.retrieve()
                .bodyToMono(RecipeAPIDTO.class);
        System.out.println(response);*/
        return client.post()
                .uri("/ourURL")
                .bodyValue("someData")
                .retrieve()
                .bodyToMono(RecipeAPIDTO.class);
    }
    @GetMapping("/testWebClient2")
    public Mono<RecipeAPIDTO> testWebClient2() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .build();
        return client.get()
                .uri("/recipes2/665540")
                .retrieve()
                .bodyToMono(RecipeAPIDTO.class);
    }
    @GetMapping("/recipes2/{id}")
    public Mono<RecipeAPIDTO> getRecipeById(@PathVariable(name = "id") Long recipeId) {
        WebClient client = WebClient.builder()
                .baseUrl("https://api.spoonacular.com/recipes/" + recipeId + "/information")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.get()
                .uri(uriBuilder -> uriBuilder.queryParam("apiKey", API_KEY).build())
                .retrieve()
                .bodyToMono(RecipeAPIDTO.class)
                .onErrorResume(throwable -> {
                    return Mono.just(new RecipeAPIDTO());
                });
    }


}
