package br.com.gama.bankline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.MensagemResponseDTO;
import br.com.gama.bankline.DTO.UsuarioDTO;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;
	
	private ContaService contaService;
	
	
	public MensagemResponseDTO salvarUsuario(UsuarioDTO usuarioDTO) {
		
		Usuario usuario = new UsuarioDTO().fromModel(usuarioDTO);

		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		contaService.salvarConta(usuarioSalvo);
		
		return criarMensagemResponse(usuarioSalvo.getId(), "Usuário criado. Id: ");
	}
	
	private MensagemResponseDTO criarMensagemResponse(Long id, String mensagem) {
		return MensagemResponseDTO.builder().mensagem(mensagem + id).build();
	}
}
