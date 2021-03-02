package br.com.gama.bankline.DTO;

import java.time.LocalDate;

import br.com.gama.bankline.model.PlanoConta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LancamentoDTO {
	
	private Long id;
	private LocalDate data;
	private Double valor;
	private String descricao;
	private String numConta;
	private String numContaDest;
	private PlanoConta planoConta;
	
	public LancamentoDTO(Long id, LocalDate data, Double valor, String descricao, String numConta, String numContaDest, PlanoConta planoConta) {
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.descricao = descricao;
		this.numConta = numConta;
		this.numContaDest = numContaDest;
		this.planoConta = planoConta;
	}
}
