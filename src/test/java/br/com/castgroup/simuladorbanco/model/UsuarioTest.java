package br.com.castgroup.simuladorbanco.model;

import br.com.castgroup.simuladorbanco.enums.TipoUsuarioEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
                .id(1L)
                .nome("Maria Silva")
                .email("maria@email.com")
                .cpf("12345678900")
                .senha("senha123")
                .tipo(TipoUsuarioEnum.CLIENTE.toModel())
                .build();
    }

    @Test
    void deveIdentificarUsuarioAdmin() {
        // Arrange
        usuario.setTipo(TipoUsuarioEnum.ADMIN.toModel());

        // Act & Assert
        assertTrue(usuario.isAdmin());
        assertFalse(usuario.isCliente());
    }

    @Test
    void deveIdentificarUsuarioCliente() {
        // Act & Assert
        assertFalse(usuario.isAdmin());
        assertTrue(usuario.isCliente());
    }

    @Test
    void naoDeveSerAdminQuandoTipoNull() {
        // Arrange
        usuario.setTipo(null);

        // Act & Assert
        assertFalse(usuario.isAdmin());
        assertFalse(usuario.isCliente());
    }

    @Test
    void deveCriarUsuarioComBuilder() {
        // Assert
        assertNotNull(usuario);
        assertEquals(1L, usuario.getId());
        assertEquals("Maria Silva", usuario.getNome());
        assertEquals("maria@email.com", usuario.getEmail());
        assertEquals("12345678900", usuario.getCpf());
    }
}
