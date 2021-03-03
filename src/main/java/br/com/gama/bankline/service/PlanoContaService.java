package br.com.gama.bankline.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.DataResponseDTO;
import br.com.gama.bankline.DTO.PlanoContaDTO;
import br.com.gama.bankline.exception.DataBaseException;
import br.com.gama.bankline.model.PlanoConta;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.PlanoContaRepository;
import br.com.gama.bankline.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlanoContaService {
		
	private PlanoContaRepository planoContaRepository;
	
	private UsuarioRepository usuarioRepository;
	
	
	public PlanoContaDTO salvarPlanoConta(PlanoContaDTO planoContaDTO) {
		
		Optional<Usuario> usuario = usuarioRepository.findByLogin(planoContaDTO.getLogin());
		
		
		if(usuario.isPresent()) {
			
			Usuario usuarioEncontrado = usuario.get();
			PlanoConta planoConta = new PlanoConta();
			planoConta.setDescricao(planoContaDTO.getDescricao());
			planoConta.setTipoPlanoConta(planoContaDTO.getTipoPlanoConta());
			planoConta.setUsuario(usuarioEncontrado);
			
			PlanoConta planoContaSalva = planoContaRepository.save(planoConta);
			
			return new PlanoContaDTO(planoContaSalva);
		}
		
		
		
		/*
		ModelMapper modelMapper = new ModelMapper();
		
		List<PlanoContaDTO2> results = new ArrayList<PlanoContaDTO2>();
		results.add(modelMapper.map(planoContaSalvo, new TypeToken<PlanoContaDTO2>(){}.getType()));
		
	    DataResponseDTO response = new DataResponseDTO ();
	    response.setSuccess(true);
	    response.setCount(results.size());
	    response.setData(results);
	    
	    return response;
		
		*/
		
		throw new DataBaseException("Login inválido");
	}
		
	
	public DataResponseDTO lerPlanoConta(String login) {
		Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
		
		if(usuario == null) {
			throw new DataBaseException("Login inválido");
		}
		
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<PlanoConta> planoContaSalvo = planoContaRepository.findDistinctPeopleByLastnameOrFirstname(login);
		
		List<PlanoContaDTO> results = modelMapper.map(planoContaSalvo, new TypeToken<List<PlanoContaDTO>>(){}.getType());

	    DataResponseDTO response = new DataResponseDTO ();
	    response.setSuccess(true);
	    response.setCount(planoContaSalvo.size());
	    response.setData(results);
	    return response;
//		return planoContaSalvo.map;//criarMensagemResponse((long)planoContaSalvo.size(), "PlanoConta encontrados. Id: ");
	}
	
}
