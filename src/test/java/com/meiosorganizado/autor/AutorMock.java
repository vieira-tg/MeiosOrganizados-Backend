package com.meiosorganizado.autor;

import com.meiosorganizado.assuntocitacao.application.dto.AssuntoCitacaoDTO;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import com.meiosorganizado.autor.application.dto.AutorDTO;
import com.meiosorganizado.autor.domain.Autor;
import junit.runner.Version;
import org.apache.tomcat.jni.Local;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AutorMock {

    private static String NOME = "Autor teste";
    private static String REFERENCIA = "Nome referencia";

    private static LocalDate DATA_AGORA = LocalDate.now();

    public static AutorDTO.AutorDTOBuilder umAutorDTO() {
       return AutorDTO.builder()
               .nome(NOME)
               .nomeReferenciaBibliografica(REFERENCIA)
               .dataFalecimento(DATA_AGORA)
               .dataNascimento(DATA_AGORA);
    }

    public static Autor.AutorBuilder umAutor() {
        return Autor.builder()
                .nome(NOME)
                .nomeReferenciaBibliografica(REFERENCIA)
                .dataFalecimento(DATA_AGORA)
                .dataNascimento(DATA_AGORA);
    }
}
