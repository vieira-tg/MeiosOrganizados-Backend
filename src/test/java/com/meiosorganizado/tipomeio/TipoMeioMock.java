package com.meiosorganizado.tipomeio;

import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;

public class TipoMeioMock {

    public static TipoMeio.TipoMeioBuilder umTipoMeio(){
        return TipoMeio.builder()
                .nome("Tipo de Meio de Teste");
    }

    public static TipoMeioDTO.TipoMeioDTOBuilder umTipoMeioDTO() {
        return TipoMeioDTO.builder()
                .nome("Tipo de Meio de Teste");
    }
}
