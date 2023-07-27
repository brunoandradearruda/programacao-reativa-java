package br.unipe.reactiveclass.service;

import br.unipe.reactiveclass.model.Aluno;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExternalApiAluno {
    private final WebClient webClient;

    public ExternalApiAluno(){
        this.webClient = WebClient.create("http://localhost:8081");
    }

    public Flux<Aluno> fetchDataFromExternal(){
        return webClient.get()
                .uri("/aluno")
                .retrieve()
                .bodyToFlux(Aluno.class)
                .flatMap(response -> {
                    if (response != null){
                        return Flux.just(response);
                    }else {
                        return Flux.empty();
                    }
                });
    }

    public Mono<Aluno> fetchDataFromExternalById(Long id){
        return webClient.get()
                .uri("/aluno/" + id.toString())
                .retrieve()
                .bodyToMono(Aluno.class)
                .flatMap(response -> {
                    if (response != null){
                        return Mono.just(response);
                    }else {
                        return Mono.empty();
                    }
                });
    }
}
