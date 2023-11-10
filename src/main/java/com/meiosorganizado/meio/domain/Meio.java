package com.meiosorganizado.meio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "MEIO")
public class Meio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SQ_MEIO")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "SQ_TIPO_MEIO")
    private TipoMeio tipoMeio;

    @Column(name = "DATA_HORA_CADASTRO")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss")
    private LocalDateTime dataHoraCadastro;

    @PrePersist
    public void prePersist() {
        dataHoraCadastro = LocalDateTime.now();
    }
}
