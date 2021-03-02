package br.com.gama.bankline.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.DTO.MensagemResponseDTO;
import br.com.gama.bankline.DTO.UsuarioDTO;
import br.com.gama.bankline.service.UsuarioService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/usuarios")
public class UsuarioController {

	
	private UsuarioService usuarioService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MensagemResponseDTO criarConta(@RequestBody @Valid UsuarioDTO usuarioDTO) {
		return usuarioService.salvarUsuario(usuarioDTO);
	}
}
