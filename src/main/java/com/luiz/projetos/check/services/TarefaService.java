package com.luiz.projetos.check.services;

import com.luiz.projetos.check.dto.TarefaDTO;
import com.luiz.projetos.check.dto.TarefaResponseDTO;
import com.luiz.projetos.check.exceptions.RegraDeNegocioException;
import com.luiz.projetos.check.model.Tarefa;
import com.luiz.projetos.check.model.Usuario;
import com.luiz.projetos.check.model.enumeration.Status;
import com.luiz.projetos.check.repositories.TarefaRepository;
import com.luiz.projetos.check.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Transactional
    public TarefaResponseDTO criarTarefa(TarefaDTO dto){
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RegraDeNegocioException("Código de usuário inválido: " + dto.getIdUsuario()));

        Tarefa tarefa = new Tarefa();
        tarefa.setUsuario(usuario);
        tarefa.setDescription(dto.getDescription());
        tarefa.setCreationDate(LocalDate.now());
        LocalDate dueDate = LocalDate.parse(dto.getDueDate(), DATE_PATTERN);
        tarefa.setDueDate(dueDate);
        tarefa.setStatus(Status.UNDONE);
        tarefaRepository.save(tarefa);

        return buildDTO(tarefa);
    }

    @Transactional(readOnly = true)
    public TarefaResponseDTO obterTarefa(Long id) {
        return tarefaRepository.findById(id).map(this::buildDTO)
                .orElseThrow(() -> new RegraDeNegocioException("Código de tarefa inválido: " + id));
    }

    private TarefaResponseDTO buildDTO(Tarefa tarefa){
        return TarefaResponseDTO.builder().idTarefa(tarefa.getIdTarefa())
                .idUsuario(tarefa.getUsuario().getIdUsuario())
                .nomeUsuario(tarefa.getUsuario().getName())
                .description(tarefa.getDescription())

                .dueDate(tarefa.getDueDate().format(DATE_PATTERN))
                .status(tarefa.getStatus().name())
                .creationDate(tarefa.getCreationDate().format(DATE_PATTERN))
                .build();
    }

    @Transactional
    public TarefaResponseDTO atualizarTarefa(Long idTarefa, TarefaDTO dto) {
        Tarefa tarefaAtualizada = tarefaRepository.findById(idTarefa).map(t -> {
            t.setDescription(dto.getDescription());
            t.setDueDate(LocalDate.parse(dto.getDueDate(), DATE_PATTERN));
            return t;
        }).orElseThrow(() -> new RegraDeNegocioException("Código da tarefa inválido: " + idTarefa));

        Usuario user = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RegraDeNegocioException("Código de usuário inválido: " + dto.getIdUsuario()));
        tarefaAtualizada.setUsuario(user);
        tarefaRepository.save(tarefaAtualizada);

        return buildDTO(tarefaAtualizada);
    }

    public void atualizarStatus(Long id, String statusAtualizado) {
        try {
            Status status = Status.valueOf(statusAtualizado.toUpperCase());
            Tarefa tarefa = tarefaRepository.findById(id).map(t -> {
                t.setStatus(status);
                return t;
            }).orElseThrow(() -> new RegraDeNegocioException("Código de tarefa inválido: " + id));
            tarefaRepository.save(tarefa);
        } catch (IllegalArgumentException exception){
            throw new RegraDeNegocioException("Status " + statusAtualizado + " inválido");
        }
    }

    @Transactional(readOnly = true)
    public List<TarefaResponseDTO> obterTarefas() {
        return tarefaRepository.findAll().stream().map(this::buildDTO).collect(Collectors.toList());
    }
}


