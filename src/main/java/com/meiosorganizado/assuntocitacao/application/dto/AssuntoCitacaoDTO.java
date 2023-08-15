package com.meiosorganizado.assuntocitacao.application.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class AssuntoCitacaoDTO {

    private Long id;

    @NotBlank(message = "É necessário informar a descrição!")
    private String descricao;
}
