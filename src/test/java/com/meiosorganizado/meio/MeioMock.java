package com.meiosorganizado.meio;

import com.meiosorganizado.meio.application.dto.MeioDTO;
import com.meiosorganizado.meio.domain.Meio;
import com.meiosorganizado.tipomeio.TipoMeioMock;

import java.time.LocalDateTime;

public class MeioMock {

    private static String NOME = "Meio teste";

    public static MeioDTO.MeioDTOBuilder umMeioDTO() {
        return MeioDTO.builder()
                .nome(NOME)
                .tipoMeio(TipoMeioMock.umTipoMeioDTO().build())
                .dataHoraCadastro(LocalDateTime.now());
    }

    public static Meio.MeioBuilder umMeio() {
        return Meio.builder()
                .nome(NOME)
                .tipoMeio(TipoMeioMock.umTipoMeio().build())
                .dataHoraCadastro(LocalDateTime.now());
    }
}
