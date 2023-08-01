package com.meiosorganizado.tipomeio.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoMeioRepository extends JpaRepository<TipoMeio, Long> {

    @Query(" SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM TipoMeio u " +
           " WHERE upper(u.nome) = upper(:nome) " +
           " and (:id is null or u.id <> :id) ")
    Boolean verificarExistenciaNomeIgual(@Param("id") Long id, @Param("nome") String nome);

    List<TipoMeio> findByNomeContainingIgnoreCase(String nome);
}
