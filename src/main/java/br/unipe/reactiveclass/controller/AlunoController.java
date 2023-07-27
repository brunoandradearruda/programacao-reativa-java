package br.unipe.reactiveclass.controller;


import br.unipe.reactiveclass.model.Aluno;
import br.unipe.reactiveclass.service.ExternalApiAluno;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/aluno")
@AllArgsConstructor

public class AlunoController {
    private final ExternalApiAluno externalApiAluno;

    @GetMapping
    public Flux<Aluno> getAll() {
        return externalApiAluno.fetchDataFromExternal();
    }

    @GetMapping("/{id}")
    public Mono<Aluno> getByAlunoMono(@PathVariable Long id) {
        return externalApiAluno.fetchDataFromExternalById(id);
    }

}
