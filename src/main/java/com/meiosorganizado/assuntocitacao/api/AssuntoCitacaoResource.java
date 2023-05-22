package com.meiosorganizado.assuntocitacao.api;

import com.meiosorganizado.config.Auditable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AssuntoCitacaoResource.PATH)
@Auditable
public class AssuntoCitacaoResource {

    public static final String PATH = "/assunto-citacao";

}
