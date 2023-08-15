package com.meiosorganizado.meio.application.dto;

import com.meiosorganizado.tipomeio.domain.TipoMeio;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class MeioDTO {
    private Long id;

    @NotBlank(message = "É necessário informar o nome!")
    private String nome;

    @NotNull(message = "É necessário informar o tipo do meio!")
    private TipoMeio tipoMeio;
}
