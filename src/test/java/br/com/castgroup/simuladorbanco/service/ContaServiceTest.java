package br.com.castgroup.simuladorbanco.service;

import br.com.castgroup.simuladorbanco.model.Conta;
import br.com.castgroup.simuladorbanco.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    private Conta conta;

    @BeforeEach
    void setUp() {
        conta = Conta.builder()
                .id(1L)
                .numeroConta("0000000099")
                .saldo(BigDecimal.valueOf(1000))
                .build();
    }

    @Test
    void deveExcluirContaComSucesso() {
        // Arrange
        when(contaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(contaRepository).deleteById(1L);

        // Act
        boolean resultado = contaService.excluir(1L);

        // Assert
        assertTrue(resultado);
        verify(contaRepository).existsById(1L);
        verify(contaRepository).deleteById(1L);
    }

    @Test
    void naoDeveExcluirContaInexistente() {
        // Arrange
        when(contaRepository.existsById(999L)).thenReturn(false);

        // Act
        boolean resultado = contaService.excluir(999L);

        // Assert
        assertFalse(resultado);
        verify(contaRepository).existsById(999L);
        verify(contaRepository, never()).deleteById(anyLong());
    }
}
