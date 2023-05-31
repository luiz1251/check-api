package com.luiz.projetos.check.repositories;

import com.luiz.projetos.check.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
