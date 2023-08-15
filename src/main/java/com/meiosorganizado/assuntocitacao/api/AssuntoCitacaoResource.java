package com.meiosorganizado.assuntocitacao.api;

import com.meiosorganizado.assuntocitacao.application.AssuntoCitacaoService;
import com.meiosorganizado.assuntocitacao.application.dto.AssuntoCitacaoDTO;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(AssuntoCitacaoResource.PATH)
@Auditable
public class AssuntoCitacaoResource {

    public static final String PATH = "/assunto-citacao";

    @Autowired
    private AssuntoCitacaoService assuntoCitacaoService;

    @PostMapping
    public ResponseEntity<AssuntoCitacao> criarTipo(@RequestBody @Valid AssuntoCitacaoDTO assuntoCitacaoDTO) {

        return ResponseEntity.ok().body(assuntoCitacaoService.save(assuntoCitacaoDTO));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AssuntoCitacao> buscarAssuntoId(@PathVariable Long id) {

        return ResponseEntity.ok().body(assuntoCitacaoService.findbyId(id));
    }

    @PutMapping
    public ResponseEntity<AssuntoCitacao> atualizarTipo(@RequestBody @Valid AssuntoCitacaoDTO assuntoCitacaoDTO) {

        return ResponseEntity.ok().body(assuntoCitacaoService.save(assuntoCitacaoDTO));
    }

    @GetMapping(path = "/pesquisar")
    public ResponseEntity<List<AssuntoCitacao>> pesquisar(@RequestParam String nome) {
        return ResponseEntity.ok()
                .body(this.assuntoCitacaoService.findByDescricaoContainingIgnoreCase(nome));
    }

}
