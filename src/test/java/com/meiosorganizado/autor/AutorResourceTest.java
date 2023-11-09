package com.meiosorganizado.autor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meiosorganizado.assuntocitacao.api.AssuntoCitacaoResource;
import com.meiosorganizado.autor.api.AutorResource;
import com.meiosorganizado.autor.application.AutorService;
import com.meiosorganizado.autor.application.dto.AutorDTO;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@WebMvcTest(AutorResource.class)
public class AutorResourceTest {

    private String path = AutorResource.PATH;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorService autorService;

    @Test
    void testCriarTipo() throws Exception {
        val autorDto = AutorMock.umAutorDTO().id(1l).build();
        val autor = AutorMock.umAutor().id(1l).build();

        when(autorService.save(any(AutorDTO.class))).thenReturn(autor);

        ObjectMapper objectMapper = new ObjectMapper();
        String tipoMeioDTOJson = objectMapper.writeValueAsString(autorDto);

        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .content(tipoMeioDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(autorDto.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(autorDto.getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeReferenciaBibliografica", is(autorDto.getNomeReferenciaBibliografica())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento", is(autorDto.getDataNascimento())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataFalecimento", is(autorDto.getDataFalecimento())));
    }

    @Test
    public void buscarAssuntoCitacaoPorId_DeveRetornarStatusOk() throws Exception {
        val autor = AutorMock.umAutor().id(1l).build();

        when(autorService.findbyId(anyLong())).thenReturn(autor);

        mockMvc.perform(MockMvcRequestBuilders.get(path + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(autor.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(autor.getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeReferenciaBibliografica", is(autor.getNomeReferenciaBibliografica())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento", is(autor.getDataNascimento())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataFalecimento", is(autor.getDataFalecimento())));
    }

    @Test
    public void atualizarTipoMeio_DeveRetornarStatusOk() throws Exception {
        val autorDto = AutorMock.umAutorDTO().id(1l).build();

        val autor = AutorMock.umAutor().id(1l).build();

        when(autorService.save(any(AutorDTO.class))).thenReturn(autor);

        ObjectMapper objectMapper = new ObjectMapper();
        String tipoMeioDTOJson = objectMapper.writeValueAsString(autorDto);

        mockMvc.perform(MockMvcRequestBuilders.put(path)
                        .content(tipoMeioDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(autorDto.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(autorDto.getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeReferenciaBibliografica", is(autorDto.getNomeReferenciaBibliografica())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento", is(autorDto.getDataNascimento())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataFalecimento", is(autorDto.getDataFalecimento())));
    }

    @Test
    public void pesquisarTipoMeioPorNome_DeveRetornarStatusOk() throws Exception {
        val autor = AutorMock.umAutor().id(1l).build();

        when(autorService.findByNomeContainingIgnoreCase(any(String.class)))
                .thenReturn(Collections.singletonList(autor));

        mockMvc.perform(MockMvcRequestBuilders.get(path + "/pesquisar?nome=Teste"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(autor.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(autor.getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeReferenciaBibliografica", is(autor.getNomeReferenciaBibliografica().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataNascimento", is(autor.getDataNascimento())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataFalecimento", is(autor.getDataFalecimento())));
    }
}
