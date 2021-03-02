package br.com.gama.bankline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.DTO.LoginDTO;
import br.com.gama.bankline.DTO.SessaoDTO;
import br.com.gama.bankline.service.LoginService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {
	
	private LoginService loginService;
	
	@PostMapping
	public SessaoDTO insert(@RequestBody LoginDTO loginDTO) {
		return loginService.autenticarUsuario(loginDTO);
		
	}
}
