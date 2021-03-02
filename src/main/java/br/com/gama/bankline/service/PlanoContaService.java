package br.com.gama.bankline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.MensagemResponseDTO;
import br.com.gama.bankline.DTO.PlanoContaDTO;
import br.com.gama.bankline.DTO.UsuarioDTO;
import br.com.gama.bankline.model.PlanoConta;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.PlanoContaRepository;
import br.com.gama.bankline.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlanoContaService {
	
	private PlanoContaRepository planoContaRepository;
	
	
	public MensagemResponseDTO salvarPlanoConta(PlanoContaDTO planoContaDTO) {
		
		PlanoConta planoConta = new PlanoContaDTO().fromModel(planoContaDTO);
				
		PlanoConta planoContaSalvo = planoContaRepository.save(planoConta); 
		
		return criarMensagemResponse(planoContaSalvo.getId(), "PlanoConta criado. Id: ");
	}

	public MensagemResponseDTO LerPlanoConta() {
						
		List<PlanoConta> planoContaSalvo = planoContaRepository.findAll(); 
		
		
		return criarMensagemResponse(11111L, "PlanoConta encontrados. Id: ");
	}
	

	private MensagemResponseDTO criarMensagemResponse(Long id, String mensagem) {
		return MensagemResponseDTO.builder().mensagem(mensagem + id).build();
	}
}
