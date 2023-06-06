package com.meiosorganizado.tipomeio.api;

import com.meiosorganizado.config.Auditable;
import com.meiosorganizado.tipomeio.application.TipoMeioService;
import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TipoMeioResource.PATH)
@Auditable
public class TipoMeioResource {

    public static final String PATH = "/tipo-meio";

    @Autowired
    private TipoMeioService tipoMeioService;

    @PostMapping
    public ResponseEntity<TipoMeio> criarTipo(@PathVariable TipoMeioDTO tipoMeio) {

        return ResponseEntity.ok().body(tipoMeioService.save(tipoMeio));
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<TipoMeio> buscarTipoId(@PathVariable Long id) {

        return ResponseEntity.ok().body(tipoMeioService.findbyId(id));
    }

    @PutMapping
    public ResponseEntity<TipoMeio> atualizarTipo(@PathVariable TipoMeioDTO tipoMeio) {

        return ResponseEntity.ok().body(tipoMeioService.save(tipoMeio));
    }

    /*@PutMapping
    public ResponseEntity<TipoMeio> pesquisar(@PathVariable String nome) {

        return ResponseEntity.ok().body(null);
    }*/
}
