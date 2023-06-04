package com.luiz.projetos.check.controllers;

import com.luiz.projetos.check.dto.UsuarioDTO;
import com.luiz.projetos.check.exceptions.UsuarioNaoEncontradoException;
import com.luiz.projetos.check.model.Usuario;
import com.luiz.projetos.check.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createUser(@Valid @RequestBody UsuarioDTO dto){
        Usuario usuario = Usuario.builder()
                        .email(dto.getEmail())
                        .name(dto.getName())
                        .password(dto.getPassword())
                        .tarefas(Collections.emptyList())
                                .build();
        repository.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario findById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    @PutMapping("/{id}")
    public Usuario updateUser(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto){
        return repository.findById(id).map(p -> {
            p.setName(dto.getName());
            p.setEmail(dto.getEmail());
            p.setPassword(dto.getPassword());
            repository.save(p);
            return p;
        }).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        if (!repository.findById(id).isPresent()){
            throw new UsuarioNaoEncontradoException();
        }
        repository.deleteById(id);
    }
}
