package br.com.gama.bankline.DTO;

import java.util.Date;

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
	private Date dataInicio;
	private Date dataFim;
	private String token;

	public static SessaoDTO fromDTO(Conta conta, Date dataInicio, Date dataFim, String token) {
		return new SessaoDTO(conta, dataInicio, dataFim, token);

	}
}
