package com.luiz.projetos.check.dto;

import javax.validation.constraints.NotBlank;

public class UsuarioDTO {
    @NotBlank(message = "Name is requiered")
    private String name;
    @NotBlank(message = "E-mail is requiered")
    private String email;
    @NotBlank(message = "Password is requiered")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
