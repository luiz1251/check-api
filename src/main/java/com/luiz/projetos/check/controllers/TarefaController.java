package com.luiz.projetos.check.controllers;

import com.luiz.projetos.check.dto.TarefaDTO;
import com.luiz.projetos.check.model.Tarefa;
import com.luiz.projetos.check.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa create(@Valid @RequestBody TarefaDTO dto){
        return tarefaService.create(dto);
    }
}
