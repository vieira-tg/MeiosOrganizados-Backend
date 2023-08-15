package com.meiosorganizado.assuntocitacao.domain;

import com.meiosorganizado.meio.domain.Meio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AssuntoCitacaoRepository extends JpaRepository<AssuntoCitacao, Long> {

    @Query(" SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AssuntoCitacao u " +
            " WHERE upper(u.descricao) = upper(:descricao) " +
            " and (:id is null or u.id <> :id) ")
    Boolean verificarExistenciaNomeIgual(@Param("id") Long id, @Param("descricao") String descricao);

    List<AssuntoCitacao> findByDescricaoContainingIgnoreCase(String nome);

}
