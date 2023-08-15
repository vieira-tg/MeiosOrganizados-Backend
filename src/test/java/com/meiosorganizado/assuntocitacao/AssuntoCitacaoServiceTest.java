package com.meiosorganizado.assuntocitacao;

import com.meiosorganizado.assuntocitacao.application.AssuntoCitacaoService;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacaoRepository;
import com.meiosorganizado.tipomeio.TipoMeioMock;
import com.meiosorganizado.tipomeio.application.TipoMeioService;
import com.meiosorganizado.tipomeio.domain.TipoMeio;
import com.meiosorganizado.tipomeio.domain.TipoMeioRepository;
import exception.NegocioException;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AssuntoCitacaoServiceTest {

    @Mock
    private AssuntoCitacaoRepository assuntoCitacaoRepository;

    @InjectMocks
    private AssuntoCitacaoService assuntoCitacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Given
        val assuntoCitacaoDTO = AssuntoCitacaoMock.umAssuntoCitacaoDTO().id(null).build();
        val assuntoCitacao = AssuntoCitacaoMock.umAssuntoCitacao().id(1l).build();

        when(assuntoCitacaoRepository.verificarExistenciaNomeIgual(any(), any())).thenReturn(false);
        when(assuntoCitacaoRepository.save(any(AssuntoCitacao.class))).thenReturn(assuntoCitacao);

        // When
        val savedAssuntoCitacao = assuntoCitacaoService.save(assuntoCitacaoDTO);

        // Then
        assertNotNull(savedAssuntoCitacao);
        assertEquals(1L, savedAssuntoCitacao.getId());
        assertEquals(assuntoCitacaoDTO.getDescricao(), savedAssuntoCitacao.getDescricao());

        verify(assuntoCitacaoRepository, times(1)).verificarExistenciaNomeIgual(null, assuntoCitacaoDTO.getDescricao());
        verify(assuntoCitacaoRepository, times(1)).save(any(AssuntoCitacao.class));
    }

    @Test
    void testUpdate() {
        // Given
        val assuntoCitacaoDTO = AssuntoCitacaoMock.umAssuntoCitacaoDTO().id(1l).build();
        val assuntoCitacao = AssuntoCitacaoMock.umAssuntoCitacao().id(1l).build();

        when(assuntoCitacaoRepository.findById(1L)).thenReturn(Optional.of(assuntoCitacao));
        when(assuntoCitacaoRepository.verificarExistenciaNomeIgual(any(), any())).thenReturn(false);
        when(assuntoCitacaoRepository.save(any(AssuntoCitacao.class))).thenReturn(assuntoCitacao);

        // When
        val updatedAssuntoCitacao = assuntoCitacaoService.update(assuntoCitacaoDTO);

        // Then
        assertNotNull(updatedAssuntoCitacao);
        assertEquals(1L, updatedAssuntoCitacao.getId());
        assertEquals(assuntoCitacaoDTO.getDescricao(), updatedAssuntoCitacao.getDescricao());

        verify(assuntoCitacaoRepository, times(1)).findById(1L);
        verify(assuntoCitacaoRepository, times(1)).verificarExistenciaNomeIgual(1L, assuntoCitacaoDTO.getDescricao());
        verify(assuntoCitacaoRepository, times(1)).save(any(AssuntoCitacao.class));
    }

    @Test
    void testFindbyId() {
        // Given
        val assuntoCitacao = AssuntoCitacaoMock.umAssuntoCitacao().id(1l).build();;

        when(assuntoCitacaoRepository.findById(1L)).thenReturn(Optional.of(assuntoCitacao));

        // When
        val foundAssuntoCitacao = assuntoCitacaoService.findbyId(1L);

        // Then
        assertNotNull(foundAssuntoCitacao);
        assertEquals(1L, foundAssuntoCitacao.getId());
        assertEquals(assuntoCitacao.getDescricao(), foundAssuntoCitacao.getDescricao());

        verify(assuntoCitacaoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindbyId_NotFound() {
        // Given
        when(assuntoCitacaoRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NegocioException.class, () -> assuntoCitacaoService.findbyId(1L));

        verify(assuntoCitacaoRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByNomeContainingIgnoreCase() {
        // Given
        List<AssuntoCitacao> assuntos = new ArrayList<>();
        assuntos.add(AssuntoCitacao.builder().id(1L).descricao("Descricao 1").build());
        assuntos.add(AssuntoCitacao.builder().id(2L).descricao("Descricao 2").build());
        assuntos.add(AssuntoCitacao.builder().id(3L).descricao("Outro Descricao").build());

        when(assuntoCitacaoRepository.findByDescricaoContainingIgnoreCase("Descricao")).thenReturn(assuntos);

        // When
        val foundAssuntos = assuntoCitacaoService.findByDescricaoContainingIgnoreCase("Descricao");

        // Then
        assertNotNull(foundAssuntos);
        assertEquals(3, foundAssuntos.size());

        verify(assuntoCitacaoRepository, times(1)).findByDescricaoContainingIgnoreCase("Descricao");
    }
}
