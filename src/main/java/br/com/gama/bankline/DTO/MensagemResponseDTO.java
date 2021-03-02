package br.com.gama.bankline.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MensagemResponseDTO {
	
	private String mensagem;
}
