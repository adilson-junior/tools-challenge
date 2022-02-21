package br.com.toolschalleng.pgcard.vo;

import java.io.Serializable;
import java.util.Objects;

public class Transacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String cartao; 
	private Descricao descricao;
	private FormaPagamento formaPagamento;	
	
	
	
	
	public Transacao() {
		this.descricao = new Descricao();
		this.formaPagamento = new FormaPagamento();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public Descricao getDescricao() {
		return descricao;
	}

	public void setDescricao(Descricao descricao) {
		this.descricao = descricao;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		return Objects.equals(id, other.id);
	}	
	
}
