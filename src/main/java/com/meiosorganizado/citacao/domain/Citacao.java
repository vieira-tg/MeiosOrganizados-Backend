package com.meiosorganizado.citacao.domain;

import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import com.meiosorganizado.autor.domain.Autor;
import com.meiosorganizado.meio.domain.Meio;
import jdk.jfr.Name;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CITACAO")
public class Citacao {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SQ_CITACAO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SQ_AUTOR")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "SQ_MEIO")
    private Meio meio;

    @ManyToOne
    @JoinColumn(name = "SQ_ASSUNTO_CITACAO")
    private AssuntoCitacao assunto;

    @Column(name = "DATA_HORA_CADASTRO")
    private LocalDateTime dataHoraCadastro;

    @Column(name = "DATA_HORA_ALTERACAO")
    private LocalDateTime dataHoraAlteracao;
}

