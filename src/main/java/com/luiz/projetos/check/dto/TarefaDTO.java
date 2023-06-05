package com.luiz.projetos.check.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/*
 * {
 *   "usuario_id": Long,
 *   "description": String
 *   "dueDate": String,
 *   "status": String
 * }
 *
 *
 */
@Getter
@Setter
public class TarefaDTO {
    @NotNull
    private Long idUsuario;
    @NotNull
    private String description;
    @NotNull
    private String dueDate;
}
