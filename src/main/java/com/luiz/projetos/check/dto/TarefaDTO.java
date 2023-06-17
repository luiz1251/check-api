package com.luiz.projetos.check.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TarefaDTO {
    @NotEmpty(message = "{field.description.required}")
    private String description;
    @NotEmpty(message = "{field.dueDate.required}")
    private String dueDate;
}
