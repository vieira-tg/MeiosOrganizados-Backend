package com.meiosorganizado.tipomeio.api;

import com.meiosorganizado.config.Auditable;
import com.meiosorganizado.tipomeio.application.TipoMeioService;
import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TipoMeioResource.PATH)
@Auditable
public class TipoMeioResource {

    public static final String PATH = "/tipo-meio";

    @Autowired
    private TipoMeioService tipoMeioService;

    @PostMapping
    public ResponseEntity<TipoMeio> criarTipo(@PathVariable TipoMeioDTO tipoMeio){

        return ResponseEntity.ok().body(tipoMeioService.save(tipoMeio));
    }
}
