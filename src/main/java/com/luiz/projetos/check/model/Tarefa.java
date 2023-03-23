package com.luiz.projetos.check.model;

import com.luiz.projetos.check.model.enumeration.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarefa;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String description;
    private Date creationDate;
    private Date dueDate;
    private Status status;

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
