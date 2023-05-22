package com.meiosorganizado.autor.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "AUTOR")
public class Autor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "SQ_AUTOR")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "NOME_REF_BIBLIOGRAFICA")
    private String nomeReferenciaBibliografica;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "DATA_FALECIMENTO")
    private LocalDate dataFalecimento;
}
