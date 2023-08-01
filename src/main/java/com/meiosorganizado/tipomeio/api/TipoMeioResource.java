package com.meiosorganizado.tipomeio.api;

import com.meiosorganizado.config.Auditable;
import com.meiosorganizado.tipomeio.application.TipoMeioService;
import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(TipoMeioResource.PATH)
@Auditable
public class TipoMeioResource {

    public static final String PATH = "/tipo-meio";

    @Autowired
    private TipoMeioService tipoMeioService;

    @PostMapping
    public ResponseEntity<TipoMeio> criarTipo(@RequestBody @Valid TipoMeioDTO tipoMeio) {

        return ResponseEntity.ok().body(tipoMeioService.save(tipoMeio));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TipoMeio> buscarTipoId(@PathVariable Long id) {

        return ResponseEntity.ok().body(tipoMeioService.findbyId(id));
    }

    @PutMapping
    public ResponseEntity<TipoMeio> atualizarTipo(@RequestBody @Valid TipoMeioDTO tipoMeio) {

        return ResponseEntity.ok().body(tipoMeioService.save(tipoMeio));
    }

    @GetMapping(path = "/pesquisar")
    public ResponseEntity<List<TipoMeio>> pesquisar(@RequestParam String nome) {
        return ResponseEntity.ok()
                .body(this.tipoMeioService.findByNomeContainingIgnoreCase(nome));
    }
}
