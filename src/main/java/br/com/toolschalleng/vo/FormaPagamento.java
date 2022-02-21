package br.com.toolschalleng.vo;

import java.io.Serializable;

public class FormaPagamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String tipo;
	private Integer parcelas;
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Integer getParcelas() {
		return parcelas;
	}
	
	public void setParcela(Integer parcelas) {
		this.parcelas = parcelas;
	}

}
