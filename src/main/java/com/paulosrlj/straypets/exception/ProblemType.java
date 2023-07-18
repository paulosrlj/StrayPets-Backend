package com.paulosrlj.straypets.exception;

import lombok.Getter;

@Getter
public enum ProblemType {
	MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("Recurso não encontrado"),
	ENTIDADE_EM_USO("Entidade em uso"),
	ERRO_NEGOCIO("Violação de regra de negócio"),
	PARAMETRO_INVALIDO("Parâmetro inválido"),
	DADOS_INVALIDOS("Dados inválidos"),
	ERRO_DE_SISTEMA("Erro interno no sistema");
	
	private final String title;

	private ProblemType(String title) {
		this.title = title;
	}
}
