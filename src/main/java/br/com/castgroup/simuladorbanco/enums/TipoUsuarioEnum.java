package br.com.castgroup.simuladorbanco.enums;

import br.com.castgroup.simuladorbanco.model.TipoUsuario;
import lombok.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum TipoUsuarioEnum {
    ADMIN(1, "Admin"),
    CLIENTE(2, "Cliente");

    private final int id;
    private final String nome;
    private static final Map<Integer, TipoUsuarioEnum> LISTA_IDS =
            Arrays.stream(values()).collect(Collectors.toMap(TipoUsuarioEnum::getId, e -> e));
    private static final Map<String, TipoUsuarioEnum> LISTA_NOMES =
            Arrays.stream(values()).collect(Collectors.toMap(TipoUsuarioEnum::getNome, e -> e));

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static TipoUsuarioEnum fromId(Integer id) {
        return LISTA_IDS.get(id);
    }

    public static TipoUsuarioEnum fromNome(String nome) {
        return LISTA_NOMES.get(nome);
    }

    public static TipoUsuarioEnum fromModel(TipoUsuario tipo) {
    	return LISTA_IDS.get(tipo.getId());
    }

    public TipoUsuario toModel() {
    	return new TipoUsuario(this.id, this.nome);
    }
}
