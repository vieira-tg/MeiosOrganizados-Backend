package com.meiosorganizado.assuntocitacao.application;


import com.meiosorganizado.assuntocitacao.application.dto.AssuntoCitacaoDTO;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacaoRepository;
import exception.NegocioException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssuntoCitacaoService {

    @Autowired
    private AssuntoCitacaoRepository assuntoCitacaoRepository;

    public AssuntoCitacao save(AssuntoCitacaoDTO assuntoCitacaoDTO) {

        verificarNomeRepetido(assuntoCitacaoDTO.getId(), assuntoCitacaoDTO.getDescricao());

        val tipoMeioEntidade = AssuntoCitacao.builder().id(assuntoCitacaoDTO.getId()).descricao(assuntoCitacaoDTO.getDescricao()).build();

        return this.assuntoCitacaoRepository.save(tipoMeioEntidade);
    }

    public AssuntoCitacao update(AssuntoCitacaoDTO assuntoCitacaoDTO) {

        val entidade = this.findbyId(assuntoCitacaoDTO.getId());

        verificarNomeRepetido(entidade.getId(), assuntoCitacaoDTO.getDescricao());

        entidade.setDescricao(assuntoCitacaoDTO.getDescricao());

        return this.assuntoCitacaoRepository.save(entidade);
    }

    public AssuntoCitacao findbyId(Long id) {
        return this.assuntoCitacaoRepository.findById(id).orElseThrow(() -> new NegocioException("Assunto da citação com o id " + id + " não encontrado!"));
    }

    private void verificarNomeRepetido(Long codigo, String nome) {
        if (this.assuntoCitacaoRepository.verificarExistenciaNomeIgual(codigo, nome)) {
            throw new NegocioException("Já existe um tipo com esse nome cadastrado!");
        }
    }

    public List<AssuntoCitacao> findByDescricaoContainingIgnoreCase(String descricao) {
        return this.assuntoCitacaoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

}
