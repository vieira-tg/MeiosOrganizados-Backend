package com.meiosorganizado.autor.api;

import com.meiosorganizado.assuntocitacao.application.AssuntoCitacaoService;
import com.meiosorganizado.assuntocitacao.application.dto.AssuntoCitacaoDTO;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import com.meiosorganizado.autor.application.AutorService;
import com.meiosorganizado.autor.application.dto.AutorDTO;
import com.meiosorganizado.autor.domain.Autor;
import com.meiosorganizado.config.Auditable;
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
@RequestMapping(AutorResource.PATH)
@Auditable
public class AutorResource {

    public static final String PATH = "/autor";

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<Autor> criarAutor(@RequestBody @Valid AutorDTO autorDTO) {

        return ResponseEntity.ok().body(autorService.save(autorDTO));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Autor> buscarAutorId(@PathVariable Long id) {

        return ResponseEntity.ok().body(autorService.findbyId(id));
    }

    @PutMapping
    public ResponseEntity<Autor> atualizarAutor(@RequestBody @Valid AutorDTO autorDTO) {

        return ResponseEntity.ok().body(autorService.save(autorDTO));
    }

    @GetMapping(path = "/pesquisar")
    public ResponseEntity<List<Autor>> pesquisar(@RequestParam String nome) {
        return ResponseEntity.ok()
                .body(this.autorService.findByNomeContainingIgnoreCase(nome));
    }

}
