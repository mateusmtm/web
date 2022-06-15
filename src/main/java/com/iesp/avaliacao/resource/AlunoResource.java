package com.iesp.avaliacao.resource;

import com.iesp.avaliacao.model.Aluno;
import com.iesp.avaliacao.repository.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping(path = "/alunos")
public class AlunoResource{

    private AlunoRepository alunoRepository;

    public AlunoResource(AlunoRepository alunoRepository){
        super();
        this.alunoRepository = alunoRepository;
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno){
        alunoRepository.save(aluno);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(){
        List<Aluno> alunos = new ArrayList<>();
        alunos = alunoRepository.findAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<Aluno>> getById(@PathVariable Integer id){
        Optional<Aluno> aluno;
        try {
            aluno = alunoRepository.findById(id);
            return new ResponseEntity<Optional<Aluno>>(aluno, HttpStatus.OK);
        }catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Aluno>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Optional<Aluno>> deleteById(@PathVariable Integer id){
        Optional<Aluno> aluno;
        try {
            alunoRepository.deleteById(id);
            return new ResponseEntity<Optional<Aluno>>(HttpStatus.OK);
        }catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<Aluno>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody Aluno newAluno){
        return alunoRepository.findById(id)
                .map(aluno -> {
                    aluno.setNome(newAluno.getNome());
                    aluno.setCpf(newAluno.getCpf());
                    aluno.setNota(newAluno.getNota());
                    Aluno alunooUpdated = alunoRepository.save(aluno);
                    return ResponseEntity.ok().body(alunooUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }

}