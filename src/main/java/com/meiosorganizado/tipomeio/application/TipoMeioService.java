package com.meiosorganizado.tipomeio.application;

import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import com.meiosorganizado.tipomeio.domain.TipoMeioRepository;
import exception.NegocioException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoMeioService {

    @Autowired
    private TipoMeioRepository tipoMeioRepository;

    public TipoMeio save(TipoMeioDTO tipoMeioDto) {

        verificarNomeRepetido(tipoMeioDto.getId(), tipoMeioDto.getNome());

        val tipoMeioEntidade = TipoMeio.builder()
                .id(tipoMeioDto.getId())
                .nome(tipoMeioDto.getNome())
                .build();

        return this.tipoMeioRepository.save(tipoMeioEntidade);
    }

    public TipoMeio update(TipoMeioDTO tipoMeioDto) {

        val entidade = this.findbyId(tipoMeioDto.getId());

        verificarNomeRepetido(entidade.getId(), tipoMeioDto.getNome());

        entidade.setNome(tipoMeioDto.getNome());

        return this.tipoMeioRepository.save(entidade);
    }

    public TipoMeio findbyId(Long id) {
        return this.tipoMeioRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Tipo com o id " + id + " não encontrado!"));
    }

    private void verificarNomeRepetido(Long codigo, String nome) {
        if (this.tipoMeioRepository.verificarExistenciaNomeIgual(codigo, nome)) {
            throw new NegocioException("Já existe um tipo com esse nome cadastrado!");
        }
    }

    public List<TipoMeio> findByNomeContainingIgnoreCase(String nome) {
        return this.tipoMeioRepository.findByNomeContainingIgnoreCase(nome);
    }
}
