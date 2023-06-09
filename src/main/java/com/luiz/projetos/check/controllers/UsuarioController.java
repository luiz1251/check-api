package com.luiz.projetos.check.controllers;

import com.luiz.projetos.check.dto.UsuarioDTO;
import com.luiz.projetos.check.exceptions.RegraDeNegocioException;
import com.luiz.projetos.check.exceptions.UsuarioNaoEncontradoException;
import com.luiz.projetos.check.model.Usuario;
import com.luiz.projetos.check.repositories.UsuarioRepository;
import com.luiz.projetos.check.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createUser(@Valid @RequestBody UsuarioDTO dto){
        if(service.emailAlreadyExists(dto)){
            throw new RegraDeNegocioException("O email " + dto.getEmail() + " já está cadastrado.");
        }
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
            if(service.emailBelongsToAnotherUser(dto, p.getIdUsuario())){
                throw new RegraDeNegocioException("O email " + dto.getEmail() + " já está cadastrado.");
            }
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
        if (repository.findById(id).isEmpty()){
            throw new UsuarioNaoEncontradoException();
        }
        repository.deleteById(id);
    }

    @GetMapping()
    public List<Usuario> findAll(){
        return repository.findAll();
    }
}
