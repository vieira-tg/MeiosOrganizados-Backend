package com.meiosorganizado.tipomeio.application.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class TipoMeioDTO {

    private Long id;

    @NotBlank(message = "É necessário informar o nome!")
    private String nome;
}
