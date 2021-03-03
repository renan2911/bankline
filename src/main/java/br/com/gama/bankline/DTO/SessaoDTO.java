package br.com.gama.bankline.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessaoDTO {

	private Conta conta;
	private Usuario usuario;
	private Date dataInicio;
	private Date dataFim;
	private String token;
	
	@JsonIgnore
	private String login;

	public static SessaoDTO fromDTO(Conta conta, Usuario usuario, Date dataInicio, Date dataFim, String token, String login) {
		return new SessaoDTO(conta, usuario, dataInicio, dataFim, token, login);

	}
}
