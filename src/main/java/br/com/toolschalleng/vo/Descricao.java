package br.com.toolschalleng.vo;


import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Descricao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Float valor;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	private String estabelecimento;
	private String nsu;	
	private String codigoAutorizacao;
	private String status;
	
	public Float getValor() {
		return valor;
	}
	
	public void setValor(Float valor) {
		this.valor = valor;
	}
	
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
}
