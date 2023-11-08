package com.meiosorganizado.autor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meiosorganizado.assuntocitacao.api.AssuntoCitacaoResource;
import com.meiosorganizado.assuntocitacao.application.AssuntoCitacaoService;
import com.meiosorganizado.assuntocitacao.application.dto.AssuntoCitacaoDTO;
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

@WebMvcTest(AssuntoCitacaoResource.class)
public class AutorResourceTest {

    private String path = AssuntoCitacaoResource.PATH;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssuntoCitacaoService assuntoCitacaoService;

    @Test
    void testCriarTipo() throws Exception {
        val assuntoCitacaoDto = AutorMock.umAssuntoCitacaoDTO().id(1l).build();
        val assuntoCitacao = AutorMock.umAssuntoCitacao().id(1l).build();

        when(assuntoCitacaoService.save(any(AssuntoCitacaoDTO.class))).thenReturn(assuntoCitacao);

        ObjectMapper objectMapper = new ObjectMapper();
        String tipoMeioDTOJson = objectMapper.writeValueAsString(assuntoCitacaoDto);

        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .content(tipoMeioDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(assuntoCitacaoDto.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao", is(assuntoCitacaoDto.getDescricao())));
    }
    @Test
    public void buscarAssuntoCitacaoPorId_DeveRetornarStatusOk() throws Exception {
        val assuntoCitacao = AutorMock.umAssuntoCitacao().id(1l).build();

        when(assuntoCitacaoService.findbyId(anyLong())).thenReturn(assuntoCitacao);

        mockMvc.perform(MockMvcRequestBuilders.get(path + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(assuntoCitacao.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao", is(assuntoCitacao.getDescricao())));
    }

    @Test
    public void atualizarTipoMeio_DeveRetornarStatusOk() throws Exception {
        val assuntoCitacaoDTO = AutorMock.umAssuntoCitacaoDTO().id(1l).build();

        val assuntoCitacao = AutorMock.umAssuntoCitacao().id(1l).build();

        when(assuntoCitacaoService.save(any(AssuntoCitacaoDTO.class))).thenReturn(assuntoCitacao);

        ObjectMapper objectMapper = new ObjectMapper();
        String tipoMeioDTOJson = objectMapper.writeValueAsString(assuntoCitacaoDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(path)
                        .content(tipoMeioDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(assuntoCitacaoDTO.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descricao", is(assuntoCitacaoDTO.getDescricao())));
    }

    @Test
    public void pesquisarTipoMeioPorNome_DeveRetornarStatusOk() throws Exception {
        val assuntoCitacao = AutorMock.umAssuntoCitacao().id(1l).build();

        when(assuntoCitacaoService.findByDescricaoContainingIgnoreCase(any(String.class)))
                .thenReturn(Collections.singletonList(assuntoCitacao));

        mockMvc.perform(MockMvcRequestBuilders.get(path + "/pesquisar?nome=Teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(assuntoCitacao.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].descricao", is(assuntoCitacao.getDescricao())));
    }
}
