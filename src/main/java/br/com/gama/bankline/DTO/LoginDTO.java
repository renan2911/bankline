package br.com.gama.bankline.DTO;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
	
	@Size(min = 2, max = 20, message="O login deve até " + "{max} caracteres.")
	private String login;
	
	@Size(min=4,max=20,message="A senha é obrigatória, deve ter entre {min} e " + "{max} caracteres.")
	private String senha;
}
