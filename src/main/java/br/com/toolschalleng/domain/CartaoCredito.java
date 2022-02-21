package br.com.toolschalleng.domain;

import java.time.LocalDate;

public class CartaoCredito extends Cartao {
	
	private static final long serialVersionUID = 1L;	

	public CartaoCredito(String numero, String nome, Float saldo, Integer diaVencimento, LocalDate dataValidade,
			String codigoSeguranca, Float limite) {
		super(numero, nome, saldo, diaVencimento, dataValidade, codigoSeguranca, limite);
	}	

}


