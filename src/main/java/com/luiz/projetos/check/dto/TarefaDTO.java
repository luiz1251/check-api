package com.luiz.projetos.check.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TarefaDTO {
    @NotNull(message = "{field.idUsuario.required}")
    private Long idUsuario;
    @NotEmpty(message = "{field.description.required}")
    private String description;
    @NotEmpty(message = "{field.dueDate.required}")
    private String dueDate;
}
