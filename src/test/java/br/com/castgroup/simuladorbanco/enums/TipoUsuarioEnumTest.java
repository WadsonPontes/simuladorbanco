package br.com.castgroup.simuladorbanco.enums;

import br.com.castgroup.simuladorbanco.model.TipoUsuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoUsuarioEnumTest {

    @Test
    void deveRetornarClientePorId() {
        // Act
        TipoUsuarioEnum resultado = TipoUsuarioEnum.fromId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(TipoUsuarioEnum.CLIENTE, resultado);
        assertEquals("Cliente", resultado.getNome());
    }

    @Test
    void deveRetornarAdminPorId() {
        // Act
        TipoUsuarioEnum resultado = TipoUsuarioEnum.fromId(2);

        // Assert
        assertNotNull(resultado);
        assertEquals(TipoUsuarioEnum.ADMIN, resultado);
        assertEquals("Admin", resultado.getNome());
    }

    @Test
    void deveRetornarClientePorNome() {
        // Act
        TipoUsuarioEnum resultado = TipoUsuarioEnum.fromNome("Cliente");

        // Assert
        assertNotNull(resultado);
        assertEquals(TipoUsuarioEnum.CLIENTE, resultado);
    }

    @Test
    void deveConverterParaModel() {
        // Act
        TipoUsuario modelo = TipoUsuarioEnum.ADMIN.toModel();

        // Assert
        assertNotNull(modelo);
        assertEquals(2, modelo.getId());
        assertEquals("Admin", modelo.getNome());
    }

    @Test
    void deveConverterDeModel() {
        // Arrange
        TipoUsuario modelo = new TipoUsuario(1, "Cliente");

        // Act
        TipoUsuarioEnum resultado = TipoUsuarioEnum.fromModel(modelo);

        // Assert
        assertNotNull(resultado);
        assertEquals(TipoUsuarioEnum.CLIENTE, resultado);
    }

    @Test
    void deveRetornarNullParaIdInvalido() {
        // Act
        TipoUsuarioEnum resultado = TipoUsuarioEnum.fromId(999);

        // Assert
        assertNull(resultado);
    }

    @Test
    void deveRetornarNullParaNomeInvalido() {
        // Act
        TipoUsuarioEnum resultado = TipoUsuarioEnum.fromNome("Inexistente");

        // Assert
        assertNull(resultado);
    }
}
