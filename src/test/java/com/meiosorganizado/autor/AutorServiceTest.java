package com.meiosorganizado.autor;

import com.meiosorganizado.assuntocitacao.application.AssuntoCitacaoService;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacao;
import com.meiosorganizado.assuntocitacao.domain.AssuntoCitacaoRepository;
import com.meiosorganizado.autor.application.AutorService;
import com.meiosorganizado.autor.domain.Autor;
import com.meiosorganizado.autor.domain.AutorRepository;
import exception.NegocioException;
import lombok.val;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
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

class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Given
        val autorDto = AutorMock.umAutorDTO().id(null).build();
        val autor = AutorMock.umAutor().id(1l).build();

        when(autorRepository.verificarExistenciaNomeIgual(any(), any())).thenReturn(false);
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        // When
        val savedAutor = autorService.save(autorDto);

        // Then
        assertNotNull(savedAutor);
        assertEquals(1L, savedAutor.getId());
        assertEquals(autor.getNome(), savedAutor.getNome());
        assertEquals(autor.getNomeReferenciaBibliografica(), savedAutor.getNomeReferenciaBibliografica());
        assertEquals(autor.getDataFalecimento(), savedAutor.getDataFalecimento());
        assertEquals(autor.getDataNascimento(), savedAutor.getDataNascimento());

        verify(autorRepository, times(1)).verificarExistenciaNomeIgual(null, autorDto.getNome());
        verify(autorRepository, times(1)).save(any(Autor.class));
    }

    @Test
    void testUpdate() {
        // Given
        val autorDTO = AutorMock.umAutorDTO().id(1l).build();
        val autor = AutorMock.umAutor().id(1l).build();

        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));
        when(autorRepository.verificarExistenciaNomeIgual(any(), any())).thenReturn(false);
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        // When
        val updatedAutor = autorService.update(autorDTO);

        // Then
        assertNotNull(updatedAutor);
        assertEquals(1L, updatedAutor.getId());
        assertEquals(autor.getNome(), updatedAutor.getNome());
        assertEquals(autor.getNomeReferenciaBibliografica(), updatedAutor.getNomeReferenciaBibliografica());
        assertEquals(autor.getDataFalecimento(), updatedAutor.getDataFalecimento());
        assertEquals(autor.getDataNascimento(), updatedAutor.getDataNascimento());

        verify(autorRepository, times(1)).findById(1L);
        verify(autorRepository, times(1)).verificarExistenciaNomeIgual(1L, autorDTO.getNome());
        verify(autorRepository, times(1)).save(any(Autor.class));
    }

    @Test
    void testFindbyId() {
        // Given
        val autor = AutorMock.umAutor().id(1l).build();;

        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));

        // When
        val foundAutor = autorService.findbyId(1L);

        // Then
        assertNotNull(foundAutor);
        assertEquals(1L, foundAutor.getId());
        assertEquals(autor.getNome(), foundAutor.getNome());
        assertEquals(autor.getNomeReferenciaBibliografica(), foundAutor.getNomeReferenciaBibliografica());
        assertEquals(autor.getDataFalecimento(), foundAutor.getDataFalecimento());
        assertEquals(autor.getDataNascimento(), foundAutor.getDataNascimento());

        verify(autorRepository, times(1)).findById(1L);
    }

    @Test
    void testFindbyId_NotFound() {
        // Given
        when(autorRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NegocioException.class, () -> autorService.findbyId(1L));

        verify(autorRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByNomeContainingIgnoreCase() {
        // Given
        List<Autor> assuntos = new ArrayList<>();
        assuntos.add(Autor.builder().id(1L)
                .nome("nome 1")
                .nomeReferenciaBibliografica("nome bibliografico")
                .dataFalecimento(LocalDate.now())
                .dataFalecimento(LocalDate.now())
                .build());
        assuntos.add(Autor.builder().id(2L)
                .nome("nome 2")
                .nomeReferenciaBibliografica("nome bibliografico")
                .dataFalecimento(LocalDate.now())
                .dataFalecimento(LocalDate.now())
                .build());
        assuntos.add(Autor.builder().id(3L)
                .nome("nome 3")
                .nomeReferenciaBibliografica("nome bibliografico")
                .dataFalecimento(LocalDate.now())
                .dataFalecimento(LocalDate.now())
                .build());

        when(autorRepository.findByNomeContainingIgnoreCase("nome")).thenReturn(assuntos);

        // When
        val foundAssuntos = autorService.findByNomeContainingIgnoreCase("nome");

        // Then
        assertNotNull(foundAssuntos);
        assertEquals(3, foundAssuntos.size());

        verify(autorRepository, times(1)).findByNomeContainingIgnoreCase("nome");
    }
}
