package com.luiz.projetos.check.repositories;

import com.luiz.projetos.check.dto.TarefaResponseDTO;
import com.luiz.projetos.check.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query(value = "SELECT *\n" +
            "FROM tb_tarefas t\n" +
            "WHERE t.id_tarefa = :idTarefa AND t.usuario_id = :idUsuario", nativeQuery = true)
    Optional<Tarefa> findByIdUsuarioAndIdTarefa(Long idUsuario, Long idTarefa);
}
