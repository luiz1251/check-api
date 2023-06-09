package com.luiz.projetos.check.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class StatusDTO {
    @NotEmpty(message = "{field.status.required}")
    private String status;
}
