package br.com.gama.bankline.DTO;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

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
public class PlanoContaDTO {
	
	
	private Long id;
	
	@NotNull
	@Size(max = 350, message="A descrição deve deve até " + "{max} caracteres.")
	private String descricao;
	
	@NotNull
	@Size(min = 2, max = 20, message="O login é obrigatório")
	private String login;
	
	@NotNull
	private TipoPlanoConta tipoPlanoConta;
	
	public PlanoContaDTO (PlanoConta planoConta) {
		this.id = planoConta.getId();
		this.descricao = planoConta.getDescricao();
		Usuario usuario = planoConta.getUsuario();
		this.login = usuario.getLogin();
		this.tipoPlanoConta = planoConta.getTipoPlanoConta();
	}

}
