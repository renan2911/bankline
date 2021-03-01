package br.com.gama.bankline.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.repository.ContaRepository;

@Component
public class CriarConta implements ContaRepository{
	
	@PersistenceContext
	public EntityManager entityManager;
	
	
	public List<Conta> listar(){
		TypedQuery<Conta> query = entityManager.createQuery("from Conta", Conta.class);
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public Conta salvar(Conta conta) {
		System.out.println(conta.getUsuario().getSenha());
		return entityManager.merge(conta);
	}

}
