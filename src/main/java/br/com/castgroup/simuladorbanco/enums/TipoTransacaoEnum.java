package br.com.castgroup.simuladorbanco.enums;

import br.com.castgroup.simuladorbanco.model.TipoTransacao;
import lombok.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum TipoTransacaoEnum {
    CREDITO(1, "Crédito"),
    DEBITO(2, "Débito"),
    TRANSFERENCIA(3, "Transferência");

    private final int id;
    private final String nome;
    private static final Map<Integer, TipoTransacaoEnum> LISTA_IDS =
            Arrays.stream(values()).collect(Collectors.toMap(TipoTransacaoEnum::getId, e -> e));
    private static final Map<String, TipoTransacaoEnum> LISTA_NOMES =
            Arrays.stream(values()).collect(Collectors.toMap(TipoTransacaoEnum::getNome, e -> e));

    public static TipoTransacaoEnum fromId(Integer id) {
        return LISTA_IDS.get(id);
    }

    public static TipoTransacaoEnum fromNome(String nome) {
        return LISTA_NOMES.get(nome);
    }

    public static TipoTransacaoEnum fromModel(TipoTransacao tipo) {
        return LISTA_IDS.get(tipo.getId());
    }

    public TipoTransacao toModel() {
    	return new TipoTransacao(this.id, this.nome);
    }
}
