package br.com.toolschalleng.pgcard.service;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService<T> {	
	@Autowired
	protected T repository;

}
