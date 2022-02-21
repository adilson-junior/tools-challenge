package br.com.toolschalleng.pgcard.vo;

import java.io.Serializable;

public class TransacaoResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Transacao transacao;	

	public Transacao getTransacao() {
		return transacao;
	}

	public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}	

}
