package com.meiosorganizado.autor.application;


import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import com.meiosorganizado.autor.application.dto.AutorDTO;
import com.meiosorganizado.autor.domain.Autor;
import com.meiosorganizado.autor.domain.AutorRepository;
import exception.NegocioException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public Autor save(AutorDTO autorDTO) {

        verificarNomeRepetido(autorDTO.getId(), autorDTO.getNome());

        val autorEntidade = Autor.builder()
                .id(autorDTO.getId())
                .nome(autorDTO.getNome())
                .dataNascimento(autorDTO.getDataNascimento())
                .dataFalecimento(autorDTO.getDataFalecimento())
                .build();

        return this.autorRepository.save(autorEntidade);
    }

    public Autor update(AutorDTO autorDTO) {

        val entidade = this.findbyId(autorDTO.getId());

        verificarNomeRepetido(entidade.getId(), autorDTO.getNome());

        entidade.setNome(autorDTO.getNome());
        entidade.setNomeReferenciaBibliografica(autorDTO.getNomeReferenciaBibliografica());
        entidade.setDataNascimento(autorDTO.getDataNascimento());
        entidade.setDataFalecimento(autorDTO.getDataFalecimento());

        return this.autorRepository.save(entidade);
    }

    public Autor findbyId(Long id) {
        return this.autorRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Autor da citação com o id " + id + " não encontrado!"));
    }

    private void verificarNomeRepetido(Long codigo, String nome) {
        if (this.autorRepository.verificarExistenciaNomeIgual(codigo, nome)) {
            throw new NegocioException("Já existe um autor com esse nome cadastrado!");
        }
    }

    public List<Autor> findByNomeContainingIgnoreCase(String descricao) {
        return this.autorRepository.findByNomeContainingIgnoreCase(descricao);
    }

}
