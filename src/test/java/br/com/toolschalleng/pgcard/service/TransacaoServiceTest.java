package br.com.toolschalleng.pgcard.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.toolschalleng.pgcard.domain.Cartao;
import br.com.toolschalleng.pgcard.domain.Pagamento;
import br.com.toolschalleng.pgcard.enuns.FormaPagamentoUnum;
import br.com.toolschalleng.pgcard.enuns.Status;
import br.com.toolschalleng.pgcard.repository.CartaoPagamentoResository;
import br.com.toolschalleng.pgcard.util.TransacaoUTIL;
import br.com.toolschalleng.pgcard.vo.Transacao;
import br.com.toolschalleng.pgcard.vo.TransacaoResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransacaoServiceTest extends AbstractService<CartaoPagamentoResository>{
	
	Transacao transacao;
	
	@Before
	public void init() {
		transacao = new Transacao();
		transacao.setCartao("5387444452528983");
		transacao.getDescricao().setValor(100f);
		transacao.getDescricao().setDataHora(LocalDateTime.now());
		transacao.getDescricao().setEstabelecimento("Pet 100%");
		transacao.getFormaPagamento().setTipo(FormaPagamentoUnum.AVISTA.getValor());
		transacao.getFormaPagamento().setParcela(1);		
	}
	
	@Test
	public void validaRealizarPagamento() {
		TransacaoUTIL util = new TransacaoUTIL();   
		Cartao cartao = repository.buscarCartao(transacao.getCartao());
		Float saldo = transacao.getDescricao().getValor() - cartao.getSaldo();
		String codigoAutorizacao = util.getCodigoAutorizacao();
		String nsu = util.getProximoNsu();
		cartao.setSaldo(saldo);
		repository.atualizaSaldoCartao(cartao);
		Pagamento pagamento = new Pagamento();
		pagamento.setCartao(transacao.getCartao());
		pagamento.setCodigoAutorizacao(codigoAutorizacao);
		pagamento.setNsu(nsu);
		pagamento.setDataPagamento(transacao.getDescricao().getDataHora());
		pagamento.setValor(transacao.getDescricao().getValor());
		pagamento.setEstabelecimento(transacao.getDescricao().getEstabelecimento());
		if(transacao.getFormaPagamento().getTipo().equals("AVISTA")) {
			pagamento.setForma(FormaPagamentoUnum.AVISTA);
		}else if(transacao.getFormaPagamento().getTipo().equals("PARCELADO LOJA")){
			pagamento.setForma(FormaPagamentoUnum.PARCELADO_LOJA);
		}else {
			pagamento.setForma(FormaPagamentoUnum.PARCELADO_EMISSOR);
		}
		pagamento.setParcelas(transacao.getFormaPagamento().getParcelas());
		pagamento.setStatus(Status.AUTORIZADO);
		pagamento = repository.salvarPagamento(pagamento);
	}
	
	@Test
	public void validarConsultarPagamento() {
		TransacaoUTIL util = new TransacaoUTIL();  
		Long id = 1l;
		Pagamento pagamento = repository.buscarPagamentoId(id);
		if(pagamento != null) {
			//Prepara resposta
			//Prepara resposta
			Transacao transacao = new Transacao();
			transacao.setId(pagamento.getId());
			transacao.setCartao(util.esconderNumCartao(pagamento.getCartao()));
			transacao.getDescricao().setCodigoAutorizacao(pagamento.getCodigoAutorizacao());
			transacao.getDescricao().setDataHora(pagamento.getDataPagamento());
			transacao.getDescricao().setNsu(pagamento.getNsu());
			transacao.getDescricao().setStatus(pagamento.getStatus().getValor());
			transacao.getDescricao().setValor(pagamento.getValor());
			transacao.getDescricao().setEstabelecimento(pagamento.getEstabelecimento());
			transacao.getFormaPagamento().setTipo(pagamento.getForma().getValor());
			transacao.getFormaPagamento().setParcela(pagamento.getParcelas());
		}
	}
	
	@Test
	public void validarEstornoPamamento() {
		TransacaoUTIL util = new TransacaoUTIL(); 
		Long id = 1l;
		Pagamento pagamento = repository.buscarPagamentoId(id);
		if(pagamento != null) {
			Cartao cartao = repository.buscarCartao(pagamento.getCartao());
			Float saldo = pagamento.getValor() + cartao.getSaldo();
			cartao.setSaldo(saldo);
			repository.atualizaSaldoCartao(cartao);
			pagamento.setStatus(Status.CANCELADO);
			repository.estorno(pagamento);
			//Prepara resposta
			Transacao transacao = new Transacao();
			transacao.setId(pagamento.getId());
			transacao.setCartao(util.esconderNumCartao(pagamento.getCartao()));
			transacao.getDescricao().setCodigoAutorizacao(pagamento.getCodigoAutorizacao());
			transacao.getDescricao().setDataHora(pagamento.getDataPagamento());
			transacao.getDescricao().setNsu(pagamento.getNsu());
			transacao.getDescricao().setStatus(pagamento.getStatus().getValor());
			transacao.getDescricao().setValor(pagamento.getValor());
			transacao.getDescricao().setEstabelecimento(pagamento.getEstabelecimento());
			transacao.getFormaPagamento().setTipo(pagamento.getForma().getValor());
			transacao.getFormaPagamento().setParcela(pagamento.getParcelas());
		}
	}
	
	@Test
	public void validarConsultarPagamentos() {
		TransacaoUTIL util = new TransacaoUTIL();
		List<TransacaoResponse> listaResponse = new ArrayList<>();		
		for(Pagamento pagamento : repository.getPagamentos()) {
			TransacaoResponse response = new TransacaoResponse();
			Cartao cartao = repository.buscarCartao(pagamento.getCartao());
			Float saldo = pagamento.getValor() + cartao.getSaldo();
			cartao.setSaldo(saldo);
			repository.atualizaSaldoCartao(cartao);
			pagamento.setStatus(Status.CANCELADO);
			repository.estorno(pagamento);
			//Prepara resposta
			Transacao transacao = new Transacao();
			transacao.setId(pagamento.getId());
			transacao.setCartao(util.esconderNumCartao(pagamento.getCartao()));
			transacao.getDescricao().setCodigoAutorizacao(pagamento.getCodigoAutorizacao());
			transacao.getDescricao().setDataHora(pagamento.getDataPagamento());
			transacao.getDescricao().setNsu(pagamento.getNsu());
			transacao.getDescricao().setStatus(pagamento.getStatus().getValor());
			transacao.getDescricao().setValor(pagamento.getValor());
			transacao.getDescricao().setEstabelecimento(pagamento.getEstabelecimento());
			transacao.getFormaPagamento().setTipo(pagamento.getForma().getValor());
			transacao.getFormaPagamento().setParcela(pagamento.getParcelas());
			response.setTransacao(transacao);
			listaResponse.add(response);							
		}
	}
	

}
