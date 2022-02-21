package br.com.toolschalleng.pgcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

@RestController
public abstract class AbstractController<T> {
	
	@Autowired
	protected T service;

	protected ObjectMapper mapper = new ObjectMapper();
	protected Gson gson = new Gson();
	
	protected ObjectNode getMensagem(String mensagem) {		
		ObjectNode node = mapper.createObjectNode();
		node.put("mensagem", mensagem);
		return mapper.valueToTree(node);
	}

}
