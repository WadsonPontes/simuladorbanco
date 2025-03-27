package br.com.castgroup.simuladorbanco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacaoUsuarioEnum {
	SUCESSO(1, true, "Sucesso no cadastro!"),
    ERRO_NOME_NULL(2, false, "Campo nome obrigatório"),
    ERRO_EMAIL_NULL(3, false, "Campo e-mail obrigatório"),
    ERRO_SENHA_NULL(4, false, "Campo senha obrigatório"),
    ERRO_EMAIL_EXISTE(5, false, "E-mail já cadastrado"),
    ERRO_SENHA_FRACA(6, false, "A senha deve ter no mínimo 8 caracteres");
	
	private final int id;
	private final boolean sucesso;
    private final String descricao;
}
