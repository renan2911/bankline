package br.com.gama.bankline.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.gama.bankline.model.Conta;


public interface ContaRepository {
	
	public List<Conta> listar();
	public Conta salvar(Conta conta);
}
