package br.com.toolschalleng.pgcard.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.toolschalleng.pgcard.domain.Cartao;
import br.com.toolschalleng.pgcard.domain.CartaoCredito;
import br.com.toolschalleng.pgcard.domain.Pagamento;

@Repository
public class CartaoPagamentoResository {
	//Cria as listas static para simular o banco.
	private static List<CartaoCredito> listaCartoes = new ArrayList<>();
	private static Long idSequencialCartao = 1L;
	
	private static List<Pagamento>  listaPagamentos = new ArrayList<>();
	private static Long idSequencialPagamento = 1L;
	//Intância os cartões e add na lista.
	static {		
		CartaoCredito cartao1 = new CartaoCredito("5387444452528983", "João da Silva", 5000f, 20, LocalDate.of(2029, 07, 22), "633", 5000f);
		cartao1.setId(idSequencialCartao++);
		listaCartoes.add(cartao1);
		CartaoCredito cartao2 = new CartaoCredito("5387444452528985", "Roberto Carlos", 5000f, 5, LocalDate.of(2025, 05, 25), "754", 5000f);
		cartao2.setId(idSequencialCartao++);
		listaCartoes.add(cartao2);
		CartaoCredito cartao3 = new CartaoCredito("5387444452528986", "Mel Maia", 5000f, 10, LocalDate.of(2028, 07, 22), "554", 5000f);
		cartao3.setId(idSequencialCartao++);
		listaCartoes.add(cartao3);
		CartaoCredito cartao4 = new CartaoCredito("5387444452528987", "Luiza Mel", 5000f, 15, LocalDate.of(2027, 07, 22), "777", 5000f);
		cartao4.setId(idSequencialCartao++);
		listaCartoes.add(cartao4);			
	}	
		
	public List<CartaoCredito> getCartoes() {
		return CartaoPagamentoResository.listaCartoes;
	}
		
	public CartaoCredito buscarCartao(String numero) {
		for(CartaoCredito cartao : listaCartoes) {
			if(cartao.getNumero().equals(numero)) {
				return cartao;
			}
		}
		return null;
	}

	public Pagamento salvarPagamento(Pagamento pagamento) {	
		pagamento.setId(idSequencialPagamento++);		
		listaPagamentos.add(pagamento);	
		return pagamento;
	}
	
	public Pagamento buscarPagamentoId(Long id) {
		for(Pagamento pagamento : listaPagamentos) {
			if(pagamento.getId() == id) {
				return pagamento;
			}
		}
		return null;
	}

	public void atualizaSaldoCartao(Cartao cartao) {
		for(Cartao cartaoIt : listaCartoes) {
			if(cartao.getId() == cartaoIt.getId()) {
				cartaoIt.setSaldo(cartao.getSaldo());
			}		
	    }	
    }

	public void estorno(Pagamento pagamento) {
		for(Pagamento pg : listaPagamentos) {
			if(pg.getId() == pagamento.getId()) {
				pg = pagamento;
			}
		}		
		
	}

	public List<Pagamento> getPagamentos() {
		return listaPagamentos;
	}	
	
}



