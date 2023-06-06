package com.luiz.projetos.check.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaResponseDTO {
    private Long idTarefa;
    private Long idUsuario;
    private String nomeUsuario;
    private String description;
    private String creationDate;
    private String dueDate;
    private String status;
}
