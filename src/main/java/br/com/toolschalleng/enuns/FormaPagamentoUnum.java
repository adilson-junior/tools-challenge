package br.com.toolschalleng.enuns;

public enum FormaPagamentoUnum {
	
	AVISTA("AVISTA"), 
	PARCELADO_LOJA("PARCELADO LOJA"),
	PARCELADO_EMISSOR("PARCELADO EMISSOR");
	
	private String valor;

	FormaPagamentoUnum(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}

}
