package com.luiz.projetos.check.repositories;

import com.luiz.projetos.check.dto.TarefaResponseDTO;
import com.luiz.projetos.check.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query(value = "SELECT * " +
            "FROM tb_tarefas t " +
            "WHERE t.id_tarefa = :idTarefa AND t.usuario_id = :idUsuario", nativeQuery = true)
    Optional<Tarefa> findByIdUsuarioAndIdTarefa(Long idUsuario, Long idTarefa);

    @Query(value = "SELECT t from Tarefa t WHERE t.usuario.idUsuario = :idUsuario")
    List<Tarefa> findAllByIdUsuario(Long idUsuario);

    @Query(value = "SELECT t FROM Tarefa t " +
            "WHERE t.idTarefa = :idTarefa " +
            "AND t.usuario.idUsuario = :idUsuario")
    Optional<Tarefa> findByIdTarefaAndIdUsuario(Long idTarefa, Long idUsuario);
}
