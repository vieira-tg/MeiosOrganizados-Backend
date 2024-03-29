package com.meiosorganizado.autor.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.meiosorganizado.util.CustomLocalDateDeserializer;
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
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFalecimento;
}
