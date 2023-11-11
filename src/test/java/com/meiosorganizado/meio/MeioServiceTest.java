package com.meiosorganizado.meio;

import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import com.meiosorganizado.autor.application.AutorService;
import com.meiosorganizado.autor.domain.Autor;
import com.meiosorganizado.autor.domain.AutorRepository;
import com.meiosorganizado.meio.application.MeioService;
import com.meiosorganizado.meio.domain.Meio;
import com.meiosorganizado.meio.domain.MeioRepository;
import com.meiosorganizado.tipomeio.TipoMeioMock;
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

class MeioServiceTest {

    @Mock
    private MeioRepository meioRepository;

    @Mock
    private TipoMeioRepository tipoMeioRepository;

    @InjectMocks
    private MeioService meioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Given
        val meioDto = MeioMock.umMeioDTO().id(null).build();
        val meio = MeioMock.umMeio().id(1l).build();

        when(meioRepository.verificarExistenciaNomeIgual(any(), any())).thenReturn(false);
        when(tipoMeioRepository.findById(any())).thenReturn(Optional.ofNullable(TipoMeioMock.umTipoMeio().build()));
        when(meioRepository.save(any(Meio.class))).thenReturn(meio);

        // When
        val savedMeio = meioService.save(meioDto);

        // Then
        assertNotNull(savedMeio);
        assertEquals(1L, savedMeio.getId());
        assertEquals(meio.getNome(), savedMeio.getNome());
        assertEquals(meio.getDataHoraCadastro(), savedMeio.getDataHoraCadastro());
        assertEquals(meio.getTipoMeio().getId(), savedMeio.getTipoMeio().getId());
        assertEquals(meio.getTipoMeio().getNome(), savedMeio.getTipoMeio().getNome());

        verify(meioRepository, times(1)).verificarExistenciaNomeIgual(null, meioDto.getNome());
        verify(meioRepository, times(1)).save(any(Meio.class));
    }

    @Test
    void testUpdate() {
        // Given
        val meioDTO = MeioMock.umMeioDTO().id(1l).build();
        val meio = MeioMock.umMeio().id(1l).build();

        when(meioRepository.findById(1L)).thenReturn(Optional.of(meio));
        when(meioRepository.verificarExistenciaNomeIgual(any(), any())).thenReturn(false);
        when(meioRepository.save(any(Meio.class))).thenReturn(meio);
        when(tipoMeioRepository.findById(any())).thenReturn(Optional.ofNullable(TipoMeioMock.umTipoMeio().build()));

        // When
        val updatedMeio = meioService.update(meioDTO);

        // Then
        assertNotNull(updatedMeio);
        assertEquals(1L, updatedMeio.getId());
        assertEquals(meio.getNome(), updatedMeio.getNome());
        assertEquals(meio.getDataHoraCadastro(), updatedMeio.getDataHoraCadastro());
        assertEquals(meio.getTipoMeio().getId(), updatedMeio.getTipoMeio().getId());
        assertEquals(meio.getTipoMeio().getNome(), updatedMeio.getTipoMeio().getNome());

        verify(meioRepository, times(1)).findById(1L);
        verify(meioRepository, times(1)).verificarExistenciaNomeIgual(1L, meioDTO.getNome());
        verify(meioRepository, times(1)).save(any(Meio.class));
    }

    @Test
    void testFindbyId() {
        // Given
        val meio = MeioMock.umMeio().id(1l).build();;

        when(meioRepository.findById(1L)).thenReturn(Optional.of(meio));

        // When
        val foundMeio = meioService.findbyId(1L);

        // Then
        assertNotNull(foundMeio);
        assertEquals(1L, foundMeio.getId());
        assertEquals(1L, foundMeio.getId());
        assertEquals(meio.getNome(), foundMeio.getNome());
        assertEquals(meio.getDataHoraCadastro(), foundMeio.getDataHoraCadastro());
        assertEquals(meio.getTipoMeio().getId(), foundMeio.getTipoMeio().getId());
        assertEquals(meio.getTipoMeio().getNome(), foundMeio.getTipoMeio().getNome());

        verify(meioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindbyId_NotFound() {
        // Given
        when(meioRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NegocioException.class, () -> meioService.findbyId(1L));

        verify(meioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByNomeContainingIgnoreCase() {
        // Given
        List<Meio> meios = new ArrayList<>();
        meios.add(Meio.builder().id(1L).nome("nome 1").build());
        meios.add(Meio.builder().id(2L).nome("nome 2").build());
        meios.add(Meio.builder().id(3L).nome("nome Descricao").build());

        when(meioRepository.findByNomeContainingIgnoreCase("nome")).thenReturn(meios);

        // When
        val foundMeios = meioService.findByNomeContainingIgnoreCase("nome");

        // Then
        assertNotNull(foundMeios);
        assertEquals(3, foundMeios.size());

        verify(meioRepository, times(1)).findByNomeContainingIgnoreCase("nome");
    }
}
