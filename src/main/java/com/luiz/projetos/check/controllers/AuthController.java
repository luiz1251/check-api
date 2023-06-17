package com.luiz.projetos.check.controllers;

import com.luiz.projetos.check.dto.JWTToken;
import com.luiz.projetos.check.dto.LoginDTO;
import com.luiz.projetos.check.dto.UsuarioDTO;
import com.luiz.projetos.check.exceptions.RegraDeNegocioException;
import com.luiz.projetos.check.repositories.UsuarioRepository;
import com.luiz.projetos.check.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/signin")
    public JWTToken login(@RequestBody LoginDTO loginDTO){
        return service.autenticar(loginDTO);
    }

    @PostMapping("/signup")
    @ResponseStatus(CREATED)
    public void createUser(@RequestBody @Valid UsuarioDTO dto){
        if(service.emailAlreadyExists(dto)){
            throw new RegraDeNegocioException("O email " + dto.getEmail() + " já está cadastrado.");
        }
        service.save(dto);
    }
}
