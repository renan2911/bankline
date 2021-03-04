package br.com.gama.bankline.DTO;

import java.time.LocalDate;

import br.com.gama.bankline.model.Lancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoResponseDTO {
	
	private LocalDate data;
	
	private Double valor;
	
	private String descricao;
	
	private String numConta;
	
	private String numContaDest;
	
	private Long idPlanoConta;
	
	private Double saldoConta;
	
	public LancamentoResponseDTO(Lancamento lancamento) {
		this.saldoConta = lancamento.getConta().getSaldo();
		this.data = lancamento.getData();
		this.valor = lancamento.getValor();
		this.descricao = lancamento.getDescricao();
		this.numConta = lancamento.getNumConta();
		this.numContaDest = lancamento.getNumContaDest();
		this.idPlanoConta = lancamento.getPlanoConta().getId();
	}
	
	public LancamentoResponseDTO fromDTO(Lancamento lancamento) {
		return new LancamentoResponseDTO(lancamento);
	}
}
