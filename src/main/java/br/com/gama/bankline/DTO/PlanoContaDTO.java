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
	@Size(max = 350)
	private String descricao;
	
	@NotNull
	private TipoPlanoConta tipoPlanoConta;
	
	public PlanoConta fromModel(PlanoContaDTO usuarioDTO) {
		return new PlanoConta(usuarioDTO.getDescricao(), usuarioDTO.getTipoPlanoConta());
	}
	
	public static PlanoContaDTO fromDTO(PlanoConta usuario) {
		return new PlanoContaDTO(usuario.getDescricao(), usuario.getTipoPlanoConta());
	}
	

}
