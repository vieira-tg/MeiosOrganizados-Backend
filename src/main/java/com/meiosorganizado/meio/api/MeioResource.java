package com.meiosorganizado.meio.api;

import com.meiosorganizado.config.Auditable;
import com.meiosorganizado.meio.application.MeioService;
import com.meiosorganizado.meio.application.dto.MeioDTO;
import com.meiosorganizado.meio.domain.Meio;
import com.meiosorganizado.tipomeio.application.TipoMeioService;
import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MeioResource.PATH)
@Auditable
public class MeioResource {

    public static final String PATH = "/meio";

    @Autowired
    private MeioService meioService;

    @PostMapping
    public ResponseEntity<Meio> criarMeio(@PathVariable MeioDTO meio){

        return ResponseEntity.ok().body(meioService.save(meio));
    }
}
