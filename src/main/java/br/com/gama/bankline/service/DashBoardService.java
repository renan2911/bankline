package br.com.gama.bankline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.ContaDTO;
import br.com.gama.bankline.DTO.DashBoardDTO;
import br.com.gama.bankline.exception.DataBaseException;
import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.model.Lancamento;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.ContaRepository;
import br.com.gama.bankline.repository.LancamentoRepository;
import br.com.gama.bankline.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DashBoardService {

	private UsuarioRepository usuarioRepository;
	
	private LancamentoRepository lancamentoRepository;
	
	private ContaRepository contaRepository;
	
	
	public DashBoardDTO listarDashBoard(String login) {
		
		Optional<Usuario> usuarioPorLogin = usuarioRepository.findByLogin(login);
		
		DashBoardDTO dashBoardDTO = new DashBoardDTO();
		
		if(usuarioPorLogin.isPresent()) {
			
			Optional<Conta> contaPorLogin= contaRepository.findDistinctPeopleByLastnameOrFirstname(login); 
			Conta conta = contaPorLogin.get();
			List<Lancamento> lancamentoPorNumConta = lancamentoRepository.findTop10ByContaIdOrderByDataDesc(conta.getId());
			ContaDTO contaDTO = new ContaDTO(conta, lancamentoPorNumConta);
			
			dashBoardDTO.setContaDTO(contaDTO);
		}else {
			throw new DataBaseException("Login inválido");
		}
		
		return dashBoardDTO;
	}
	
}
