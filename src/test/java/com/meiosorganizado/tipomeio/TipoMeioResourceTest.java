package com.meiosorganizado.tipomeio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meiosorganizado.tipomeio.api.TipoMeioResource;
import com.meiosorganizado.tipomeio.application.TipoMeioService;
import com.meiosorganizado.tipomeio.application.dto.TipoMeioDTO;
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

@WebMvcTest(TipoMeioResource.class)
public class TipoMeioResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TipoMeioService tipoMeioService;

    @Test
    public void criarTipoMeio_DeveRetornarStatusOk() throws Exception {
        val tipoMeioDTO = TipoMeioMock.umTipoMeioDTO().id(1l).build();
        val tipoMeioCriado = TipoMeioMock.umTipoMeio().id(1l).build();

        when(tipoMeioService.save(any(TipoMeioDTO.class))).thenReturn(tipoMeioCriado);

        ObjectMapper objectMapper = new ObjectMapper();
        String tipoMeioDTOJson = objectMapper.writeValueAsString(tipoMeioDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/tipo-meio")
                        .content(tipoMeioDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(tipoMeioDTO.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(tipoMeioDTO.getNome())));
    }

    @Test
    public void buscarTipoMeioPorId_DeveRetornarStatusOk() throws Exception {
        val tipoMeio = TipoMeioMock.umTipoMeio().id(1l).build();

        when(tipoMeioService.findbyId(anyLong())).thenReturn(tipoMeio);

        mockMvc.perform(MockMvcRequestBuilders.get("/tipo-meio/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(tipoMeio.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(tipoMeio.getNome())));
    }

    @Test
    public void atualizarTipoMeio_DeveRetornarStatusOk() throws Exception {
        val tipoMeioDTO = TipoMeioMock.umTipoMeioDTO().id(1l).build();

        val tipoMeioAtualizado = TipoMeioMock.umTipoMeio().id(1l).build();

        when(tipoMeioService.save(any(TipoMeioDTO.class))).thenReturn(tipoMeioAtualizado);

        ObjectMapper objectMapper = new ObjectMapper();
        String tipoMeioDTOJson = objectMapper.writeValueAsString(tipoMeioDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/tipo-meio")
                        .content(tipoMeioDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(tipoMeioDTO.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(tipoMeioDTO.getNome())));
    }

    @Test
    public void pesquisarTipoMeioPorNome_DeveRetornarStatusOk() throws Exception {
        val tipoMeio = TipoMeioMock.umTipoMeio().id(1l).build();

        when(tipoMeioService.findByNomeContainingIgnoreCase(any(String.class)))
                .thenReturn(Collections.singletonList(tipoMeio));

        mockMvc.perform(MockMvcRequestBuilders.get("/tipo-meio/pesquisar?nome=Teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(tipoMeio.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", is(tipoMeio.getNome())));
    }
}
