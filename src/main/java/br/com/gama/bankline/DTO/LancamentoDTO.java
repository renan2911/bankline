package br.com.gama.bankline.DTO;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.model.Lancamento;
import br.com.gama.bankline.model.PlanoConta;
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
	private PlanoConta planoConta;
	
	@NotNull
	private Conta conta;
	
	
	
	public Lancamento fromModel(LancamentoDTO lancamentoDTO) {
		return new Lancamento(lancamentoDTO.getData(), lancamentoDTO.getValor(), lancamentoDTO.getDescricao(), 
				lancamentoDTO.getNumConta(), lancamentoDTO.getNumContaDest(), lancamentoDTO.getPlanoConta(), 
				lancamentoDTO.getConta());
	}
	
	public static LancamentoDTO fromDTO(Lancamento Lancamento) {
		return new LancamentoDTO(Lancamento.getData(), Lancamento.getValor(), Lancamento.getDescricao()
				, Lancamento.getNumConta(), Lancamento.getNumContaDest(), Lancamento.getPlanoConta(), 
				Lancamento.getConta());
	}
	
	public LancamentoDTO(Lancamento lancamento) {
		this.id = lancamento.getId();
		this.data = lancamento.getData();
		this.valor = lancamento.getValor();
		this.descricao = lancamento.getDescricao();
		this.numConta = lancamento.getNumConta();
		this.numContaDest = lancamento.getNumContaDest();
		this.planoConta = lancamento.getPlanoConta();
	}
}
