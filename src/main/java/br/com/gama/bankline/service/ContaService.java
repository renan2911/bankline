package br.com.gama.bankline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.ContaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContaService {
	
	private ContaRepository contaRepository;

	
	public void salvarConta(Usuario usuario) {
		
		Conta conta = new Conta(usuario.getLogin(), usuario);

		contaRepository.save(conta);
		
	}
	
	
}
