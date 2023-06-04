package com.luiz.projetos.check.repositories;

import com.luiz.projetos.check.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    void deleteById(Long id);
}
