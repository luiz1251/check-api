package com.luiz.projetos.check.repositories;

import com.luiz.projetos.check.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    void deleteById(Long id);

    @Query("select u from Usuario u where u.email = :email")
    Optional<Usuario> usuarioAlreadyExists(@Param("email") String email);

    @Query("select u from Usuario u where u.email = :email and u.id = :id")
    Optional<Usuario> emailBelongsToAnother(@Param("email") String email, @Param("id") Long id);

    Optional<Usuario> findByName(String username);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
