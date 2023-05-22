package com.meiosorganizado.meio.application.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MeioDTO {
    private Long id;
    private String nome;
}
