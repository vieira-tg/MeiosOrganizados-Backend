package com.meiosorganizado.tipomeio.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TIPO_MEIO")
public class TipoMeio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SQ_TIPO_MEIO")
    private Long id;

    @Column(name = "NOME")
    private String nome;
}

