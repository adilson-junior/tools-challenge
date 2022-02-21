package br.com.toolschalleng.pgcard.exception;

import br.com.toolschalleng.pgcard.vo.TransacaoResponse;

public class NegocioException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private TransacaoResponse transacao;

	public NegocioException(TransacaoResponse transacao) {
		super();
		this.transacao = transacao;
	}

	public TransacaoResponse getTransacao() {
		return transacao;
	}

	public void setTransacao(TransacaoResponse transacao) {
		this.transacao = transacao;
	}
		
}

