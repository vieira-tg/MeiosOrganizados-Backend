package com.meiosorganizado.tipomeio.application.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TipoMeioDTO {
    private Long id;
    private String nome;
}
