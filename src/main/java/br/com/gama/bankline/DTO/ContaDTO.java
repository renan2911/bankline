package br.com.gama.bankline.DTO;

import java.util.List;

import javax.websocket.ContainerProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.model.Lancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {
	
	
	private Long id;
	private List<Lancamento> lancamentos;
	private Double saldo;
	
	public ContaDTO(Conta conta, List<Lancamento> lancamentos) {
		this.id = conta.getId();
		this.saldo = conta.getSaldo();
		this.lancamentos = lancamentos;
	}
}
