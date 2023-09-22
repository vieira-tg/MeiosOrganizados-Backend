package com.meiosorganizado.autor.domain;

import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query(" SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Autor u " +
            " WHERE upper(u.nome) = upper(:nome) " +
            " and (:id is null or u.id <> :id) ")
    Boolean verificarExistenciaNomeIgual(@Param("id") Long id, @Param("nome") String nome);

    List<Autor> findByNomeContainingIgnoreCase(String nome);
}
