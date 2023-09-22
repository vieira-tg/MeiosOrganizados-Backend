package com.meiosorganizado.autor.application.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Data
public class AutorDTO {

    private Long id;

    @NotBlank(message = "É necessário informar a descrição!")
    private String nome;

    @NotBlank(message = "É necessário informar a descrição!")
    private String nomeReferenciaBibliografica;

    @NotNull(message = "É necessário informar a data de nascimento!")
    private LocalDate dataNascimento;

    private LocalDate dataFalecimento;
}
