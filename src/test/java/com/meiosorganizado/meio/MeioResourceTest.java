package com.meiosorganizado.meio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meiosorganizado.meio.api.MeioResource;
import com.meiosorganizado.meio.application.MeioService;
import com.meiosorganizado.meio.application.dto.MeioDTO;
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

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@WebMvcTest(MeioResource.class)
public class MeioResourceTest {

    private String path = MeioResource.PATH;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeioService meioService;


    @Test
    void testCriarMeio() throws Exception {
        val meioDto = MeioMock.umMeioDTO().id(1l).build();
        val meio = MeioMock.umMeio().id(1l).build();

        when(meioService.save(any(MeioDTO.class))).thenReturn(meio);

        ObjectMapper objectMapper = new ObjectMapper();
        String tipoMeioDTOJson = objectMapper.writeValueAsString(meioDto);

        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .content(tipoMeioDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(meioDto.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(meioDto.getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoMeio.id", is(meioDto.getTipoMeio().getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoMeio.nome", is(meioDto.getTipoMeio().getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataHoraCadastro", is(meioDto.getDataHoraCadastro().format(ofPattern("dd/MM/yyyy HH:mm:ss")))));
    }

    @Test
    public void buscarAssuntoCitacaoPorId_DeveRetornarStatusOk() throws Exception {
        val meio = MeioMock.umMeio().id(1l).build();

        when(meioService.findbyId(anyLong())).thenReturn(meio);

        mockMvc.perform(MockMvcRequestBuilders.get(path + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(meio.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(meio.getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoMeio.id", is(meio.getTipoMeio().getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoMeio.nome", is(meio.getTipoMeio().getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataHoraCadastro", is(meio.getDataHoraCadastro().format(ofPattern("dd/MM/yyyy HH:mm:ss")))));
    }

    @Test
    public void atualizarTipoMeio_DeveRetornarStatusOk() throws Exception {
        val meioDTO = MeioMock.umMeioDTO().id(1l).build();

        val meio = MeioMock.umMeio().id(1l).build();

        when(meioService.save(any(MeioDTO.class))).thenReturn(meio);

        ObjectMapper objectMapper = new ObjectMapper();
        String meioDTOJson = objectMapper.writeValueAsString(meioDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(path)
                        .content(meioDTOJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(meioDTO.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", is(meioDTO.getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoMeio.id", is(meioDTO.getTipoMeio().getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipoMeio.nome", is(meioDTO.getTipoMeio().getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataHoraCadastro", is(meioDTO.getDataHoraCadastro().format(ofPattern("dd/MM/yyyy HH:mm:ss")))));
    }

    @Test
    public void pesquisarTipoMeioPorNome_DeveRetornarStatusOk() throws Exception {
        val meio = MeioMock.umMeio().id(1l).build();

        when(meioService.findByNomeContainingIgnoreCase(any(String.class)))
                .thenReturn(Collections.singletonList(meio));

        mockMvc.perform(MockMvcRequestBuilders.get(path + "/pesquisar?nome=Teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(meio.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", is(meio.getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tipoMeio.id", is(meio.getTipoMeio().getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tipoMeio.nome", is(meio.getTipoMeio().getNome())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dataHoraCadastro", is(meio.getDataHoraCadastro().format(ofPattern("dd/MM/yyyy HH:mm:ss")))));
    }
}
