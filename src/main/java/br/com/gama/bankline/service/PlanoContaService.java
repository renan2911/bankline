package br.com.gama.bankline.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.DataResponseDTO;
import br.com.gama.bankline.DTO.MensagemResponseDTO;
import br.com.gama.bankline.DTO.PlanoContaDTO;
import br.com.gama.bankline.model.PlanoConta;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.PlanoContaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlanoContaService {
		
	private PlanoContaRepository planoContaRepository;
	
	
	public DataResponseDTO salvarPlanoConta(PlanoContaDTO planoContaDTO) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		PlanoConta planoConta = new PlanoContaDTO().fromModel(planoContaDTO);
		
		PlanoConta planoContaSalvo = planoContaRepository.save(planoConta);
		
		List<PlanoContaDTO> results = new ArrayList<PlanoContaDTO>();
		results.add(modelMapper.map(planoContaSalvo, new TypeToken<PlanoContaDTO>(){}.getType()));
		
	    DataResponseDTO response = new DataResponseDTO ();
	    response.setSuccess(true);
	    response.setCount(results.size());
	    response.setData(results);
	    
	    return response;
		
//		return criarMensagemResponse(planoContaSalvo.getId(), "PlanoConta criado. Id: ");
	}
		
	public DataResponseDTO LerPlanoConta() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<PlanoConta> planoContaSalvo = planoContaRepository.findAll();
		
		List<PlanoContaDTO> results = modelMapper.map(planoContaSalvo, new TypeToken<List<PlanoContaDTO>>(){}.getType());

	    DataResponseDTO response = new DataResponseDTO ();
	    response.setSuccess(true);
	    response.setCount(planoContaSalvo.size());
	    response.setData(results);
	    return response;
//		return planoContaSalvo.map;//criarMensagemResponse((long)planoContaSalvo.size(), "PlanoConta encontrados. Id: ");
	}
	

	private MensagemResponseDTO criarMensagemResponse(Long id, String mensagem) {
		return MensagemResponseDTO.builder().mensagem(mensagem + id).build();
	}
	
}
