package com.meiosorganizado.meio.application;

import com.meiosorganizado.meio.application.dto.MeioDTO;
import com.meiosorganizado.meio.domain.Meio;
import com.meiosorganizado.meio.domain.MeioRepository;
import com.meiosorganizado.tipomeio.application.TipoMeioService;
import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import com.meiosorganizado.tipomeio.domain.TipoMeioRepository;
import exception.NegocioException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeioService {

    @Autowired
    private MeioRepository meioRepository;

    @Autowired
    private TipoMeioService tipoMeioService;

    public Meio save(MeioDTO meioDto) {

        verificarNomeRepetido(meioDto.getId(), meioDto.getNome());

        val tipoMeioEntidade = Meio.builder()
                .id(meioDto.getId())
                .nome(meioDto.getNome())
                .tipoMeio(tipoMeioService.findbyId(meioDto.getTipoMeio().getId()))
                .build();

        return this.meioRepository.save(tipoMeioEntidade);
    }

    public Meio update(MeioDTO meioDto) {

        val entidade = this.findbyId(meioDto.getId());

        verificarNomeRepetido(entidade.getId(), meioDto.getNome());

        entidade.setNome(meioDto.getNome());
        entidade.setTipoMeio(tipoMeioService.findbyId(meioDto.getTipoMeio().getId()));

        return this.meioRepository.save(entidade);
    }

    public Meio findbyId(Long id) {
        return this.meioRepository.findById(id)
                .orElseThrow(() -> new NegocioException("O Meio com o id " + id + " não encontrado!"));
    }

    private void verificarNomeRepetido(Long codigo, String nome) {
        if (this.meioRepository.verificarExistenciaNomeIgual(codigo, nome)) {
            throw new NegocioException("Já existe um meio com esse nome cadastrado!");
        }
    }

    public List<Meio> findByNomeContainingIgnoreCase(String nome) {
        return this.meioRepository.findByNomeContainingIgnoreCase(nome);
    }
}
