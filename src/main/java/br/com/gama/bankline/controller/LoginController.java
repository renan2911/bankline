package br.com.gama.bankline.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.DTO.LoginDTO;
import br.com.gama.bankline.DTO.MensagemResponseDTO;
import br.com.gama.bankline.DTO.NovaSenhaDTO;
import br.com.gama.bankline.DTO.SenhaTemporariaDTO;
import br.com.gama.bankline.DTO.SessaoDTO;
import br.com.gama.bankline.service.LoginService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {
	
	private LoginService loginService;

	@PostMapping
	@ApiOperation("Login")
	public SessaoDTO logar(@RequestBody LoginDTO loginDTO) {
		return loginService.autenticarUsuario(loginDTO);
	}
	
	@PostMapping("/alterarSenha")
	@ApiOperation("Alteração de senha")
	public SessaoDTO alterarSenha(@RequestBody @Valid NovaSenhaDTO novaSenhaDTO){
		return loginService.alterarSenha(novaSenhaDTO);

	}
	
	@PostMapping("/solicitarSenha")
	@ApiOperation("Senha temporária")
	public MensagemResponseDTO solicitarSenha(@RequestBody @Valid SenhaTemporariaDTO senhaTemporariaDTO){
		return loginService.solicitarSenhaTemporaria(senhaTemporariaDTO);

	}
	
}
