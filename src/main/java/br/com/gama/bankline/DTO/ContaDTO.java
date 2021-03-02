package br.com.gama.bankline.DTO;

import java.util.ArrayList;

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

	private ArrayList<Lancamento> lancamentos;
	private Conta conta;
	
	public ContaDTO(Conta conta, ArrayList<Lancamento> lancamentos) {
		this.conta = conta;
		this.lancamentos = lancamentos;
	}
}
