package br.com.gama.bankline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.LancamentoDTO;
import br.com.gama.bankline.DTO.LancamentoResponseDTO;
import br.com.gama.bankline.enums.TipoPlanoConta;
import br.com.gama.bankline.exception.DataBaseException;
import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.model.Lancamento;
import br.com.gama.bankline.model.PlanoConta;
import br.com.gama.bankline.repository.ContaRepository;
import br.com.gama.bankline.repository.LancamentoRepository;
import br.com.gama.bankline.repository.PlanoContaRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LancamentoService {
	
	private LancamentoRepository lancamentoRepository;
	private ContaRepository contaRepository;
	private PlanoContaRepository planoContaRepository;
	
	public LancamentoResponseDTO salvarLancamento(LancamentoDTO lancamentoDTO) {	
		
		
		Conta conta = contaRepository.findByNumero(lancamentoDTO.getNumConta());
		PlanoConta planoConta = planoContaRepository.getOne(lancamentoDTO.getIdPlanoConta());
		
		Lancamento lancamento = new Lancamento();
		lancamento.setConta(conta);
		lancamento.setData(lancamentoDTO.getData());
		lancamento.setDescricao(lancamentoDTO.getDescricao());
		lancamento.setNumConta(lancamentoDTO.getNumConta());
		lancamento.setPlanoConta(planoConta);
		lancamento.setValor(lancamentoDTO.getValor());
		lancamento.setConta(conta);
		
		if((planoConta.getTipoPlanoConta() == TipoPlanoConta.D) && conta.VerificarSaldo(lancamentoDTO.getValor())) {
			conta.Sacar(lancamento.getValor());
			lancamentoRepository.save(lancamento);
		}else if((planoConta.getTipoPlanoConta() == TipoPlanoConta.T && conta.VerificarSaldo(lancamentoDTO.getValor()) && lancamento.getNumContaDest() != null)) {
			
		}else {
			conta.Depositar(lancamento.getValor());
		}
		
		contaRepository.save(conta);
		
		try {
		}catch (Exception e) {
			throw new DataBaseException("Erro durante o cadastro de lançamento.");
		}
		
		return new LancamentoResponseDTO().fromDTO(lancamento);
	}
}