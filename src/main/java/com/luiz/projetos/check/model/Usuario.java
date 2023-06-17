package com.luiz.projetos.check.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luiz.projetos.check.model.enumeration.Roles;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario {
    public Usuario(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long idUsuario;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Tarefa> tarefas;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private Roles roles;

}
