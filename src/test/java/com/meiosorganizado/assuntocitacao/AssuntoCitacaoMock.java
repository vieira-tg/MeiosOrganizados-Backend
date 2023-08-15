package com.meiosorganizado.assuntocitacao;

import com.meiosorganizado.assuntocitacao.application.dto.AssuntoCitacaoDTO;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;

public class AssuntoCitacaoMock {

    public static AssuntoCitacao.AssuntoCitacaoBuilder umAssuntoCitacao(){
        return AssuntoCitacao.builder()
                .descricao("Assunto citação");
    }

    public static AssuntoCitacaoDTO.AssuntoCitacaoDTOBuilder umAssuntoCitacaoDTO() {
        return AssuntoCitacaoDTO.builder()
                .descricao("Assunto citação");
    }
}
