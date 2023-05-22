package com.meiosorganizado.meio.domain;

import com.meiosorganizado.tipomeio.domain.TipoMeio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeioRepository extends JpaRepository<Meio, Long> {

}
