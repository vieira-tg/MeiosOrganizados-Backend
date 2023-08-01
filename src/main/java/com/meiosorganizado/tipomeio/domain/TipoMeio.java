package com.meiosorganizado.tipomeio.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TIPO_MEIO")
public class TipoMeio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SQ_TIPO_MEIO")
    private Long id;

    @Column(name = "NOME")
    private String nome;
}

