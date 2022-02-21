package br.com.toolschalleng.pgcard.enuns;

public enum Status {
		
	AUTORIZADO("AUTORIZADO"), 
	NEGADO("NEGADO"),
	CANCELADO("CANCELADO");
	
	private String valor;

	Status(String valor) {
		
		this.valor = valor;
		}
		
		public String getValor() {
			return valor;
		}

	

}
