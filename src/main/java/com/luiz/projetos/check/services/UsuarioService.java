package com.luiz.projetos.check.services;

import com.luiz.projetos.check.dto.UsuarioDTO;
import com.luiz.projetos.check.model.Usuario;
import com.luiz.projetos.check.repositories.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    public ResponseEntity<Usuario> createUser(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        return ResponseEntity.ok(repository.save(usuario));
    }

    public ResponseEntity<Usuario> updateUser(Long id, UsuarioDTO dto) throws Exception {
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isPresent()){
            Usuario updatedUser = copyProperties(dto, usuario.get());
            return ResponseEntity.ok(updatedUser);
        } else {
            throw new Exception("Usuário não encontrado.");
        }
    }

    private Usuario copyProperties(UsuarioDTO dto, Usuario usuario){
        Usuario updatedUser = new Usuario();
        updatedUser.setIdUsuario(usuario.getIdUsuario());
        updatedUser.setName(dto.getName());
        updatedUser.setEmail(dto.getEmail());
        updatedUser.setPassword(dto.getPassword());
        updatedUser.setTarefas(usuario.getTarefas());
        return updatedUser;
    }

    public void delete(Long id) throws Exception {
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isPresent()){
            repository.delete(usuario.get());
        } else {
            throw new Exception("Usuário não encontrado.");
        }
    }

    public Optional<Usuario> findById(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario;
    }
}
