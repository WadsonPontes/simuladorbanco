package br.com.castgroup.simuladorbanco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacaoEnum {
	SUCESSO(1, true, "Sucesso no cadastro!"),
	ERRO_CPF_NULL(3, false, "Campo CPF obrigatório"),
    ERRO_NOME_NULL(2, false, "Campo nome obrigatório"),
    ERRO_EMAIL_NULL(3, false, "Campo e-mail obrigatório"),
    ERRO_SENHA_NULL(4, false, "Campo senha obrigatório"),
    ERRO_EMAIL_EXISTE(5, false, "E-mail já cadastrado"),
    ERRO_SENHA_FRACA(6, false, "A senha deve ter no mínimo 8 caracteres"),
    ERRO_EMAIL_NAO_EXISTE(7, false, "Usuário não cadastrado"),
    ERRO_SENHA_ERRADA(8, false, "Senha incorreta"),
    SUCESSO_CREDITO(9, true, "Valor creditado com sucesso!"),
    ERRO_NEGATIVO(10, true, "Insira um valor maior que zero"),
    ERRO_SALDO(11, true, "Saldo insuficiente"),
    SUCESSO_DEBITO(12, true, "Valor debitado com sucesso!"),
    SUCESSO_TRANSFERENCIA(13, true, "Valor transferido com sucesso!"),
    ERRO_CONTA(14, true, "Número da conta incorreto"),
    ERRO_AUTOTRANSFERENCIA(15, true, "Conta de destino não pode ser a mesma da origem");
	
	private final int id;
	private final boolean sucesso;
    private final String descricao;
}
