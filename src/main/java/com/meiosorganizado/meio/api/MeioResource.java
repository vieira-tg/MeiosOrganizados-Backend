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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(MeioResource.PATH)
@Auditable
public class MeioResource {
    public static final String PATH = "/meio";

    @Autowired
    private MeioService meioService;

    @PostMapping
    public ResponseEntity<Meio> criarMeio(@RequestBody @Valid MeioDTO meioDTO) {

        return ResponseEntity.ok().body(meioService.save(meioDTO));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Meio> buscarTipoId(@PathVariable Long id) {

        return ResponseEntity.ok().body(meioService.findbyId(id));
    }

    @PutMapping
    public ResponseEntity<Meio> atualizarTipo(@RequestBody @Valid MeioDTO meioDTO) {

        return ResponseEntity.ok().body(meioService.save(meioDTO));
    }

    @GetMapping(path = "/pesquisar")
    public ResponseEntity<List<Meio>> pesquisar(@RequestParam String nome) {
        return ResponseEntity.ok()
                .body(this.meioService.findByNomeContainingIgnoreCase(nome));
    }
}
