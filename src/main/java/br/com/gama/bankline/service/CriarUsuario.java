package br.com.gama.bankline.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gama.bankline.enums.ContaTipo;
import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.ContaRepository;
import br.com.gama.bankline.repository.UsuarioRepository;

@Component
public class CriarUsuario implements UsuarioRepository {

	@Autowired
	ContaRepository contaRepository;
	
	@PersistenceContext
	public EntityManager entityManager;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuario = entityManager.merge(usuario);
		Conta conta = new Conta();
		conta.setContaTipo(ContaTipo.CB);
		conta.setNumero(usuario.getLogin());
		conta.setSaldo(0.0);
		conta.setUsuario(usuario);
		contaRepository.salvar(conta);
		return usuario;
		
	}
	
	public List<Usuario> listar(){
		TypedQuery<Usuario> query =	entityManager.createQuery("from Usuario", Usuario.class);
		return query.getResultList();
		
	}
	
}
