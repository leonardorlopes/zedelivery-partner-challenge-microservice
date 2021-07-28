package br.com.zedelivery.microservices.exceptions;

public enum Messages {

	P_NOT_EXISTS("Parceiro não encontrado."), PS_NOT_EXISTS("Parceiros não encontrados."),
	PS_NOT_FOUND("Não há parceiros próximos a localização."), PS_FIND_ERROR("Erro ao obter parceiro mais próximo"),
	PS_INSERT_FAILED("Erro ao incluir parceiro:"), INVALID_ADRESS("Propriedade adress não informado"),
	INVALID_DOCUMENT("Documento já cadastrado para outro parceiro."),
	EMPTY_VALUES("Latitude e(ou) Longitude não informadas.");

	private String msg;

	Messages(final String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
