package com.luiz.projetos.check.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    @NotEmpty(message = "{field.name.required}")
    private String name;
    @NotEmpty(message = "{field.email.required}")
    private String email;
    @NotEmpty(message = "{field.password.required}")
    private String password;
}
