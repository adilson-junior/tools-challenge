package br.com.toolschalleng.pgcard.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.toolschalleng.pgcard.exception.NegocioException;
import br.com.toolschalleng.pgcard.service.TransacaoService;
import br.com.toolschalleng.pgcard.vo.TransacaoRequest;
import br.com.toolschalleng.pgcard.vo.TransacaoResponse;


@RestController
@RequestMapping("api/pagamento")
public class TransacaoController extends AbstractController<TransacaoService> {
	
	@PostMapping()
    private ResponseEntity<TransacaoResponse> realizarPagar(@RequestBody TransacaoRequest transacaoRequest) {
		try {
			return ResponseEntity.ok().body(service.realizarPagamento(transacaoRequest.getTransacao()));		
		} catch (NegocioException e) {
			return ResponseEntity.badRequest().body(e.getTransacao());
		} 			
	}	
	
	@GetMapping()
    private ResponseEntity<List<TransacaoResponse>> consultarPagamentos() {		
		return ResponseEntity.ok().body(service.consultarPagamentos());						
	}
	
	@GetMapping(value = "/{id}")
    private ResponseEntity<TransacaoResponse> consultarPagamento(@PathVariable(value = "id") Long id) {
		try {
			return ResponseEntity.ok().body(service.consultarPagamento(id));		
		} catch (NegocioException e) {
			return ResponseEntity.badRequest().body(e.getTransacao());
		} 			
	}
	
	@PutMapping(value = "/{id}")
	private ResponseEntity<TransacaoResponse> estornarPagamento(@PathVariable(value = "id") Long id) {
		try {
			return ResponseEntity.ok().body(service.estornarPagamento(id));			
		} catch (NegocioException e) {
			return ResponseEntity.badRequest().body(e.getTransacao());
		} 			
	}
	
}
