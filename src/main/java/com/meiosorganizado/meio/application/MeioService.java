package com.meiosorganizado.meio.application;

import com.meiosorganizado.meio.application.dto.MeioDTO;
import com.meiosorganizado.meio.domain.Meio;
import com.meiosorganizado.meio.domain.MeioRepository;
import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import com.meiosorganizado.tipomeio.domain.TipoMeioRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeioService {
    @Autowired
    private MeioRepository meioRepository;

    public Meio save(MeioDTO meio) {


        val meioEntidade = this.meioRepository.findById(meio.getId()).orElse(new Meio());

        if (meioEntidade != null) {

            if (meioEntidade.getId() != meio.getId()
                    && meioEntidade.getNome().toUpperCase().equals(meio.getNome().toUpperCase())) {

            }

        }

        meioEntidade.setId(meio.getId());
        meioEntidade.setNome(meio.getNome());

        return this.meioRepository.save(meioEntidade);

    }
}
