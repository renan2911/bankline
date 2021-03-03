package br.com.gama.bankline.DTO;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.gama.bankline.enums.TipoPlanoConta;
import br.com.gama.bankline.model.PlanoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanoContaDTO {
	
	@NotNull
	@Size(max = 350, message="A descrição deve deve até " + "{max} caracteres.")
	private String descricao;
	
	@Size(min = 2, max = 20, message="O login deve é obrigatório")
	private String login;
	
	@NotNull
	private TipoPlanoConta tipoPlanoConta;
	
	public PlanoContaDTO (PlanoConta planoConta) {
		descricao = planoConta.getDescricao();
		login = planoConta.getUsuario().getLogin();
		tipoPlanoConta = planoConta.getTipoPlanoConta();
	}
	
	
	/*
	public PlanoConta fromModel(PlanoContaInsertDTO usuarioDTO) {
		return new PlanoConta(usuarioDTO.getDescricao(), usuarioDTO.getTipoPlanoConta());
	}
	
	public static PlanoContaInsertDTO fromDTO(PlanoConta usuario) {
		return new PlanoContaInsertDTO(usuario.getDescricao(), usuario.getTipoPlanoConta());
	}
	*/

}
