package com.luiz.projetos.check.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotNull(message = "{field.email.required}")
    private String login;
    @NotNull(message = "{field.password.required}")
    private String password;
}
