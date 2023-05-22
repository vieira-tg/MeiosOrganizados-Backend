package com.meiosorganizado.assuntocitacao.domain;

import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface AssuntoCitacaoRepository extends JpaRepository<AssuntoCitacao, Integer> {

}
