package br.com.gama.bankline.DTO;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaTemporariaDTO {
	
	@Size(min = 2, max = 20, message="O login deve até " + "{max} caracteres.")
	private String login;
	
	@Size(min = 2, max = 20, message="O login deve até " + "{max} caracteres.")
	private String email;
}
