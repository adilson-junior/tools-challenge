package br.com.toolschalleng.service;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService<T> {	
	@Autowired
	protected T repository;

}
