package com.luiz.projetos.check.controllers;

import com.luiz.projetos.check.model.Tarefa;
import com.luiz.projetos.check.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

//    @PostMapping()
//    public ResponseEntity<Tarefa> createTask(@RequestBody Tarefa tarefa){
//        try {
//
//        } catch (Exception e){
//
//        }
//    }
}
