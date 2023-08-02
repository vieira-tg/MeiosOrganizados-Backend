package com.meiosorganizado.tipomeio;

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

class TipoMeioServiceTest {

    @Mock
    private TipoMeioRepository tipoMeioRepository;

    @InjectMocks
    private TipoMeioService tipoMeioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Given
        val tipoMeioDto = TipoMeioMock.umTipoMeioDTO().id(null).build();
        val tipoMeioEntidade = TipoMeioMock.umTipoMeio().id(1l).build();

        when(tipoMeioRepository.verificarExistenciaNomeIgual(any(), any())).thenReturn(false);
        when(tipoMeioRepository.save(any(TipoMeio.class))).thenReturn(tipoMeioEntidade);

        // When
        val savedTipoMeio = tipoMeioService.save(tipoMeioDto);

        // Then
        assertNotNull(savedTipoMeio);
        assertEquals(1L, savedTipoMeio.getId());
        assertEquals(tipoMeioDto.getNome(), savedTipoMeio.getNome());

        verify(tipoMeioRepository, times(1)).verificarExistenciaNomeIgual(null, tipoMeioDto.getNome());
        verify(tipoMeioRepository, times(1)).save(any(TipoMeio.class));
    }

    @Test
    void testUpdate() {
        // Given
        val tipoMeioDto = TipoMeioMock.umTipoMeioDTO().id(1l).build();
        val tipoMeioEntidade = TipoMeioMock.umTipoMeio().id(1l).build();

        when(tipoMeioRepository.findById(1L)).thenReturn(Optional.of(tipoMeioEntidade));
        when(tipoMeioRepository.verificarExistenciaNomeIgual(any(), any())).thenReturn(false);
        when(tipoMeioRepository.save(any(TipoMeio.class))).thenReturn(tipoMeioEntidade);

        // When
        TipoMeio updatedTipoMeio = tipoMeioService.update(tipoMeioDto);

        // Then
        assertNotNull(updatedTipoMeio);
        assertEquals(1L, updatedTipoMeio.getId());
        assertEquals(tipoMeioDto.getNome(), updatedTipoMeio.getNome());

        verify(tipoMeioRepository, times(1)).findById(1L);
        verify(tipoMeioRepository, times(1)).verificarExistenciaNomeIgual(1L, tipoMeioDto.getNome());
        verify(tipoMeioRepository, times(1)).save(any(TipoMeio.class));
    }

    @Test
    void testFindbyId() {
        // Given
        TipoMeio tipoMeioEntidade = TipoMeioMock.umTipoMeio().id(1l).build();;

        when(tipoMeioRepository.findById(1L)).thenReturn(Optional.of(tipoMeioEntidade));

        // When
        TipoMeio foundTipoMeio = tipoMeioService.findbyId(1L);

        // Then
        assertNotNull(foundTipoMeio);
        assertEquals(1L, foundTipoMeio.getId());
        assertEquals(tipoMeioEntidade.getNome(), foundTipoMeio.getNome());

        verify(tipoMeioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindbyId_NotFound() {
        // Given
        when(tipoMeioRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NegocioException.class, () -> tipoMeioService.findbyId(1L));

        verify(tipoMeioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByNomeContainingIgnoreCase() {
        // Given
        List<TipoMeio> tipoMeios = new ArrayList<>();
        tipoMeios.add(TipoMeio.builder().id(1L).nome("Tipo 1").build());
        tipoMeios.add(TipoMeio.builder().id(2L).nome("Tipo 2").build());
        tipoMeios.add(TipoMeio.builder().id(3L).nome("Outro Tipo").build());

        when(tipoMeioRepository.findByNomeContainingIgnoreCase("Tipo")).thenReturn(tipoMeios);

        // When
        List<TipoMeio> foundTipoMeios = tipoMeioService.findByNomeContainingIgnoreCase("Tipo");

        // Then
        assertNotNull(foundTipoMeios);
        assertEquals(3, foundTipoMeios.size());

        verify(tipoMeioRepository, times(1)).findByNomeContainingIgnoreCase("Tipo");
    }
}
