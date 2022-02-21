package br.com.toolschalleng.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Cartao  implements Serializable{
	
	protected static final long serialVersionUID = 1L;

	protected Long id;	
	protected String numero;
	protected String nome;
	protected Float saldo;
	protected Integer diaVencimento;
	protected LocalDate dataValidade;
	protected String codigoSeguranca;
	protected Float limite;
	
	public Cartao(String numero, String nome, Float saldo, Integer diaVencimento, LocalDate dataValidade,
			String codigoSeguranca, Float limite) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.saldo = saldo;
		this.diaVencimento = diaVencimento;
		this.dataValidade = dataValidade;
		this.codigoSeguranca = codigoSeguranca;
		this.limite = limite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getSaldo() {
		return saldo;
	}

	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	public Integer getDataVencimento() {
		return diaVencimento;
	}

	public void setDataVencimento(Integer dataVencimento) {
		this.diaVencimento = dataVencimento;
	}
	
	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Float getLimiti() {
		return limite;
	}

	public void setLimiti(Float limite) {
		this.limite = limite;
	}	
	
	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	
	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
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
		Cartao other = (Cartao) obj;
		return Objects.equals(id, other.id);
	}		
			
}