package br.com.gama.bankline.DTO;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.gama.bankline.model.Lancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoDTO {
	
	@NotNull
	private LocalDate data;
	
	@NotNull
	private Double valor;
	
	private String descricao;
	
	@NotNull
	private String numConta;
	
	@NotNull
	private String numContaDest;
	
	@NotNull
	private Long idPlanoConta;
	
	public LancamentoDTO(Lancamento lancamento) {
		this.data = lancamento.getData();
		this.valor = lancamento.getValor();
		this.descricao = lancamento.getDescricao();
		this.numConta = lancamento.getNumConta();
		this.numContaDest = lancamento.getNumContaDest();
		this.idPlanoConta = lancamento.getPlanoConta().getId();
	}
}
