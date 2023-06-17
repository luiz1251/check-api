package com.luiz.projetos.check.model;

import com.luiz.projetos.check.model.enumeration.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDate;

@CrossOrigin(origins = "*", maxAge = 3600)
@Getter
@Setter
@Entity
@Table(name = "tb_tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarefa;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String description;
    private LocalDate creationDate;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private Status status;

}
