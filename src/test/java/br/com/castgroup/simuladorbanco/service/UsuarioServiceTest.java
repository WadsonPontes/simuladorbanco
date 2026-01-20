package br.com.castgroup.simuladorbanco.service;

import br.com.castgroup.simuladorbanco.enums.SituacaoEnum;
import br.com.castgroup.simuladorbanco.enums.TipoUsuarioEnum;
import br.com.castgroup.simuladorbanco.model.Usuario;
import br.com.castgroup.simuladorbanco.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
        		.cpf("12345678900")
                .nome("João Silva")
                .email("joao@email.com")
                .senha("12345678")
                .build();
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        // Arrange
        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Act
        SituacaoEnum resultado = usuarioService.criar(usuario);

        // Assert
        assertEquals(SituacaoEnum.SUCESSO, resultado);
        verify(usuarioRepository).existsByEmail(usuario.getEmail());
    }

    @Test
    void naoDeveCriarUsuarioComEmailDuplicado() {
        // Arrange
        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

        // Act
        SituacaoEnum resultado = usuarioService.criar(usuario);

        // Assert
        assertEquals(SituacaoEnum.ERRO_EMAIL_EXISTE, resultado);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void naoDeveCriarUsuarioComNomeNull() {
        // Arrange
        usuario.setNome(null);

        // Act
        SituacaoEnum resultado = usuarioService.criar(usuario);

        // Assert
        assertEquals(SituacaoEnum.ERRO_NOME_NULL, resultado);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void naoDeveCriarUsuarioComEmailNull() {
        // Arrange
        usuario.setEmail(null);

        // Act
        SituacaoEnum resultado = usuarioService.criar(usuario);

        // Assert
        assertEquals(SituacaoEnum.ERRO_EMAIL_NULL, resultado);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void naoDeveCriarUsuarioComSenhaNull() {
        // Arrange
        usuario.setSenha(null);

        // Act
        SituacaoEnum resultado = usuarioService.criar(usuario);

        // Assert
        assertEquals(SituacaoEnum.ERRO_SENHA_NULL, resultado);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveListarTodosUsuarios() {
        // Arrange
        List<Usuario> usuarios = Arrays.asList(usuario, usuario);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Act
        List<Usuario> resultado = usuarioService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(usuarioRepository).findAll();
    }

    @Test
    void deveBuscarUsuarioPorId() {
        // Arrange
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        // Act
        Usuario resultado = usuarioService.buscarPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void deveRetornarNullQuandoUsuarioNaoExistir() {
        // Arrange
        when(usuarioRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Usuario resultado = usuarioService.buscarPorId(999L);

        // Assert
        assertNull(resultado);
        verify(usuarioRepository).findById(999L);
    }

    @Test
    void deveExcluirUsuarioComSucesso() {
        // Arrange
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1L);

        // Act
        boolean resultado = usuarioService.excluir(1L);

        // Assert
        assertTrue(resultado);
        verify(usuarioRepository).existsById(1L);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void naoDeveExcluirUsuarioInexistente() {
        // Arrange
        when(usuarioRepository.existsById(999L)).thenReturn(false);

        // Act
        boolean resultado = usuarioService.excluir(999L);

        // Assert
        assertFalse(resultado);
        verify(usuarioRepository).existsById(999L);
        verify(usuarioRepository, never()).deleteById(anyLong());
    }
}
