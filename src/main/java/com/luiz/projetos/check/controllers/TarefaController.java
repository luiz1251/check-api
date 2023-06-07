package com.luiz.projetos.check.controllers;

import com.luiz.projetos.check.dto.StatusDTO;
import com.luiz.projetos.check.dto.TarefaDTO;
import com.luiz.projetos.check.dto.TarefaResponseDTO;
import com.luiz.projetos.check.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TarefaResponseDTO create(@Valid @RequestBody TarefaDTO dto){
        return tarefaService.criarTarefa(dto);
    }
    @GetMapping("/{id}")
    public TarefaResponseDTO read(@PathVariable Long id){
        return tarefaService.obterTarefa(id);
    }

    @GetMapping()
    public List<TarefaResponseDTO> readAll(){
        return tarefaService.obterTarefas();
    }

    @PutMapping("/{id}")
    public TarefaResponseDTO update(@PathVariable Long id, @RequestBody TarefaDTO dto) {
        return tarefaService.atualizarTarefa(id, dto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Long id, @RequestBody StatusDTO status){
        tarefaService.atualizarStatus(id, status.getStatus());
    }
}
