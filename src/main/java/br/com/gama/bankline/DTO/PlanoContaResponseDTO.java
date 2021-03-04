package br.com.gama.bankline.DTO;

import br.com.gama.bankline.enums.TipoPlanoConta;
import br.com.gama.bankline.model.PlanoConta;
import br.com.gama.bankline.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanoContaResponseDTO {
	
	private Long id;
	
	private String descricao;
	
	private String login;
	
	private TipoPlanoConta tipoPlanoConta;
	
	public PlanoContaResponseDTO (PlanoConta planoConta) {
		this.id = planoConta.getId();
		this.descricao = planoConta.getDescricao();
		Usuario usuario = planoConta.getUsuario();
		this.login = usuario.getLogin();
		this.tipoPlanoConta = planoConta.getTipoPlanoConta();
	}

}
