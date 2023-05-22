package com.meiosorganizado.tipomeio.application;

import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import com.meiosorganizado.tipomeio.domain.TipoMeioRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TipoMeioService {

    @Autowired
    private TipoMeioRepository tipoMeioRepository;

    public TipoMeio save(TipoMeioDTO tipoMeio) {
        val tipoMeioEntidade = this.tipoMeioRepository.findById(tipoMeio.getId()).orElse(new TipoMeio());

        if(tipoMeioEntidade != null){

            if(tipoMeioEntidade.getId() != tipoMeio.getId()
                    && tipoMeioEntidade.getNome().toUpperCase().equals(tipoMeio.getNome().toUpperCase())){

            }

        }

        tipoMeioEntidade.setId(tipoMeio.getId());
        tipoMeioEntidade.setNome(tipoMeio.getNome());

        return this.tipoMeioRepository.save(tipoMeioEntidade);
    }
}
