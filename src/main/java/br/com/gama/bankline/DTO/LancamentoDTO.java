package br.com.gama.bankline.DTO;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	

	@NotBlank
	private LocalDate data;
	
	@NotBlank
	private Double valor;
	
	@NotEmpty
	@Size(min = 2, max = 250, message="A descrição é obrigatória e deve ter até " + "{max} caracteres.")
	private String descricao;
	
	@NotEmpty
	@Size(min = 2, max = 20, message="O número da conta é obrigatório e deve ter até " + "{max} caracteres.")
	private String numConta;
	
	
	private String numContaDest;
	
	@NotEmpty
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
