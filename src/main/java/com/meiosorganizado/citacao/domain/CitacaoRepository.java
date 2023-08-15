package com.meiosorganizado.citacao.domain;

import com.meiosorganizado.meio.domain.Meio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitacaoRepository extends JpaRepository<Citacao, Long> {

    @Query(" SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Citacao u " +
            " WHERE autor.id = :autorId " +
            " and meio.id = :meioId " +
            " and assunto.id = :assuntoId " +
            " and (:id is null or u.id <> :id) ")
    Boolean verificarExistenciaCitacaoIgual(
            @Param("id") Long id,
            @Param("autorId") Long autorId,
            @Param("meioId") Long meioId,
            @Param("assuntoId") Long assuntoId

    );
}
