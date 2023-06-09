package com.luiz.projetos.check.services;

import com.luiz.projetos.check.dto.UsuarioDTO;
import com.luiz.projetos.check.model.Usuario;
import com.luiz.projetos.check.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    public boolean emailAlreadyExists(UsuarioDTO dto){
        return repository.usuarioAlreadyExists(dto.getEmail()).isPresent();
    }

    public boolean emailBelongsToAnotherUser(UsuarioDTO dto, Long id) {
        return repository.emailBelongsToAnother(dto.getEmail(), id).isEmpty();
    }
}
