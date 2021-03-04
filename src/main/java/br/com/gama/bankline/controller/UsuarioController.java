package br.com.gama.bankline.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.DTO.MensagemResponseDTO;
import br.com.gama.bankline.DTO.UsuarioDTO;
import br.com.gama.bankline.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioController {

	private UsuarioService usuarioService;
	
	@PostMapping
	@ApiOperation("Criação do Usuário")
	public ResponseEntity<MensagemResponseDTO> criarConta(@RequestBody @Valid UsuarioDTO usuarioDTO) {
		return new ResponseEntity<MensagemResponseDTO>(usuarioService.salvarUsuario(usuarioDTO), HttpStatus.CREATED);
	}
	
}
