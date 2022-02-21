package br.com.toolschalleng.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.toolschalleng.domain.Cartao;
import br.com.toolschalleng.domain.Pagamento;
import br.com.toolschalleng.enuns.FormaPagamentoUnum;
import br.com.toolschalleng.enuns.Status;
import br.com.toolschalleng.exception.NegocioException;
import br.com.toolschalleng.repository.CartaoPagamentoResository;
import br.com.toolschalleng.util.TransacaoUTIL;
import br.com.toolschalleng.vo.Transacao;
import br.com.toolschalleng.vo.TransacaoResponse;

@Service
public class TransacaoService extends AbstractService<CartaoPagamentoResository>{		
			
	public TransacaoResponse realizarPagamento(Transacao transacao) {
		TransacaoUTIL util = new TransacaoUTIL();		
		TransacaoResponse response = new TransacaoResponse();
		response.setTransacao(transacao);       
		Cartao cartao = repository.buscarCartao(transacao.getCartao());
		if(cartao == null) {	
			transacao.getDescricao().setStatus(Status.NEGADO.getValor());
			throw new NegocioException(response);
		}
		if(cartao.getSaldo() < transacao.getDescricao().getValor()) {	
			transacao.getDescricao().setStatus(Status.NEGADO.getValor());
			throw new NegocioException(response);
		}
		Float saldo = cartao.getSaldo() - transacao.getDescricao().getValor() ;
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
		//Prepara a respota
		response.setTransacao(transacao);
		response.getTransacao().setCartao(util.esconderNumCartao(pagamento.getCartao()));
		response.getTransacao().setId(pagamento.getId());
		response.getTransacao().getDescricao().setCodigoAutorizacao(codigoAutorizacao);
		response.getTransacao().getDescricao().setNsu(nsu);
		response.getTransacao().getDescricao().setStatus(Status.AUTORIZADO.getValor());
		return response;
	}

	public TransacaoResponse consultarPagamento(Long id) {
		TransacaoUTIL util = new TransacaoUTIL();
		TransacaoResponse response = new TransacaoResponse();		
		Pagamento pagamento = repository.buscarPagamentoId(id);
		if(pagamento != null) {
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
		}
		return response;
	}
	
	public TransacaoResponse estornarPagamento(Long id) {
		TransacaoUTIL util = new TransacaoUTIL();
		TransacaoResponse response = new TransacaoResponse();		
		Pagamento pagamento = repository.buscarPagamentoId(id);
		if(pagamento != null) {
			Cartao cartao = repository.buscarCartao(pagamento.getCartao());
			Float saldo = cartao.getSaldo() + pagamento.getValor();
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
		}
		return response;
	}

	
	public List<TransacaoResponse> consultarPagamentos() {
		TransacaoUTIL util = new TransacaoUTIL();
		List<TransacaoResponse> listaResponse = new ArrayList<>();		
		for(Pagamento pagamento : repository.getPagamentos()) {
			TransacaoResponse response = new TransacaoResponse();
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
		return listaResponse;
	}
}
