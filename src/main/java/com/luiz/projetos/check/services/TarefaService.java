package com.luiz.projetos.check.services;

import com.luiz.projetos.check.dto.TarefaDTO;
import com.luiz.projetos.check.exceptions.RegraDeNegocioException;
import com.luiz.projetos.check.model.Tarefa;
import com.luiz.projetos.check.model.Usuario;
import com.luiz.projetos.check.model.enumeration.Status;
import com.luiz.projetos.check.repositories.TarefaRepository;
import com.luiz.projetos.check.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Tarefa create(TarefaDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RegraDeNegocioException("Código de usuário inválido: " + dto.getIdUsuario()));

        Tarefa tarefa = new Tarefa();
        tarefa.setUsuario(usuario);
        tarefa.setDescription(dto.getDescription());
        tarefa.setCreationDate(LocalDate.now());
        LocalDate dueDate = dateFormatter(dto.getDueDate());
        tarefa.setDueDate(dueDate);
        tarefa.setStatus(Status.UNDONE);
        tarefaRepository.save(tarefa);

        return tarefa;

    }

    private LocalDate dateFormatter(String date){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, pattern);
    }


}
