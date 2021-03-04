package br.com.gama.bankline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.MensagemResponseDTO;
import br.com.gama.bankline.DTO.UsuarioDTO;
import br.com.gama.bankline.enums.TipoPlanoConta;
import br.com.gama.bankline.exception.DataBaseException;
import br.com.gama.bankline.model.PlanoConta;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.PlanoContaRepository;
import br.com.gama.bankline.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	private UsuarioRepository usuarioRepository;
	
	private PlanoContaRepository planoContaRepository;
	
	private ContaService contaService;
	
	
	public MensagemResponseDTO salvarUsuario(UsuarioDTO usuarioDTO) {
		
		try {
			
			Usuario usuario = new UsuarioDTO().fromModel(usuarioDTO);
			
			String senhaCrip = encoder.encode(usuario.getSenha());
			
			usuario.setSenha(senhaCrip);
			
			Usuario usuarioSalvo = usuarioRepository.save(usuario);
			
			contaService.salvarConta(usuarioSalvo);
			
			PlanoConta planoContaD = new PlanoConta("DESPESA",TipoPlanoConta.D, usuarioSalvo);
			planoContaRepository.save(planoContaD);
			
			PlanoConta planoContaR = new PlanoConta("RECEITA",TipoPlanoConta.R, usuarioSalvo);
			planoContaRepository.save(planoContaR);
			
			PlanoConta planoContaT = new PlanoConta("TRANSFERÊNCIA",TipoPlanoConta.T, usuarioSalvo);
			planoContaRepository.save(planoContaT);
			
			return criarMensagemResponse(usuarioSalvo.getId(), "Usuário criado. Id: ");
			
		}catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Conta já existente.");
		} 
	}
	
	private MensagemResponseDTO criarMensagemResponse(Long id, String mensagem) {
		return MensagemResponseDTO.builder().mensagem(mensagem + id).build();
	}
}
