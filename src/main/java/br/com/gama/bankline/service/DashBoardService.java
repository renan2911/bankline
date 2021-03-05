package br.com.gama.bankline.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.ContaDTO;
import br.com.gama.bankline.DTO.DashBoardDTO;
import br.com.gama.bankline.exception.DataBaseException;
import br.com.gama.bankline.exception.StandardError;
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
			List<Lancamento> lancamentoPorNumConta = lancamentoRepository.findByContaIdOrderByDataDesc(conta.getId());
			ContaDTO contaDTO = new ContaDTO(conta, lancamentoPorNumConta);
			
			dashBoardDTO.setContaDTO(contaDTO);
		}else {
			throw new DataBaseException("Login inválido");
		}
		
		return dashBoardDTO;
	}


public DashBoardDTO listarDashBoardporData(String login, String dataInicioStr, String dataFimStr) {
	LocalDate dataInicio;
	LocalDate dataFim;
	
	try
	{
		dataInicio = LocalDate.parse(dataInicioStr.replaceAll("\s+",""));
	}catch (Exception e) {
		throw new DataBaseException("data inicial invalida");
	}
	
	try
	{
		dataFim = LocalDate.parse(dataFimStr.replaceAll("\s+",""));
	}catch (Exception e) {
		throw new DataBaseException("data final inválida.");
	}
	
	Optional<Usuario> usuarioPorLogin = usuarioRepository.findByLogin(login);
	List<Lancamento> lancamentoPorNumConta;
	DashBoardDTO dashBoardDTO = new DashBoardDTO();
	
	if(usuarioPorLogin.isPresent()) {
		
		Optional<Conta> contaPorLogin= contaRepository.findDistinctPeopleByLastnameOrFirstname(login); 
		Conta conta = contaPorLogin.get();
		
		lancamentoPorNumConta = lancamentoRepository.findByDataGreaterThanEqualAndDataLessThanEqualAndNumContaOrderByDataDesc(dataInicio, dataFim, login);
		
		ContaDTO contaDTO = new ContaDTO(conta, lancamentoPorNumConta);
		
		dashBoardDTO.setContaDTO(contaDTO);
	}else {
		throw new DataBaseException("Login inválido");
	}
	
	return dashBoardDTO;
}
	
}
