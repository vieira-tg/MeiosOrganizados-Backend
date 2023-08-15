package com.meiosorganizado.assuntocitacao.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "ASSUNTO_CITACAO")
public class AssuntoCitacao {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SQ_ASSUNTO_CITACAO")
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;
}
