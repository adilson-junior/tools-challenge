package br.com.toolschalleng.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import br.com.toolschalleng.enuns.FormaPagamentoUnum;
import br.com.toolschalleng.enuns.Status;

public class Pagamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;	
	private Float valor;	
	private LocalDateTime dataPagamento;	
	private String estabelecimento;	
	private String nsu;	
	private String codigoAutorizacao;	
	private String cartao;
	private Integer parcelas;
	private Status status;
	private FormaPagamentoUnum forma;	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(String estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public String getNsu() {
		return nsu;
	}

	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	public String getCodigoAutorizacao() {
		return codigoAutorizacao;
	}

	public void setCodigoAutorizacao(String codigoAutorizacao) {
		this.codigoAutorizacao = codigoAutorizacao;
	}	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public FormaPagamentoUnum getForma() {
		return forma;
	}

	public void setForma(FormaPagamentoUnum forma) {
		this.forma = forma;
	}
	
	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
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
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
			
}
