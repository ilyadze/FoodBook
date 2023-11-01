package com.example.foodbook.sevices;

import com.example.foodbook.dto.FullRecipeAPIDTO;
import com.example.foodbook.models.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
@Service
@AllArgsConstructor
public class WebClientService {
/////////////////////////////////////////////////////////////////////////////
private final String API_KEY="3345d443c0e4442c8060dee679aa8c53";
    private final WebClient webClient;
    public FullRecipeAPIDTO getRecipeById(Long id){
        return webClient.get()
                .uri(uriBuilder ->uriBuilder
                        .path("recipes/"+id+"/information")
                        .queryParam("apiKey",API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(FullRecipeAPIDTO.class)
                .doOnError(error-> System.out.println("error: " +error.getMessage()))
                .block();
    }
/////////////////////////////////////////////////////////////////////////////
    public Mono<Person> getUserByIdAsync(final String id) {
        return webClient
                .get()
                .uri(String.join("", "/users/", id))
                .retrieve()
                .bodyToMono(Person.class)
                .doOnError(error-> System.out.println("error: "+error.getMessage()))
                .onErrorResume(error -> Mono.just(new Person()));
    }
    public Person getUserByIdSync(final String id) {
        return webClient
                .get()
                .uri(String.join("", "/users/", id))
                .retrieve()
                .bodyToMono(Person.class)
                .doOnError(error-> System.out.println("error: "+error.getMessage()))
                .onErrorResume(error -> Mono.just(new Person()))
                .block();
    }
}
