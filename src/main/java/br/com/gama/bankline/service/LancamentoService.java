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
		
		
		Conta contaOrigem = contaRepository.findByNumero(lancamentoDTO.getNumConta());
		PlanoConta planoConta = planoContaRepository.getOne(lancamentoDTO.getIdPlanoConta());
		
		if((contaOrigem != null && planoConta != null)) {
			
			Lancamento lancamento = new Lancamento();
			lancamento.setConta(contaOrigem);
			lancamento.setData(lancamentoDTO.getData());
			lancamento.setDescricao(lancamentoDTO.getDescricao());
			lancamento.setNumConta(lancamentoDTO.getNumConta());
			lancamento.setPlanoConta(planoConta);
			lancamento.setValor(lancamentoDTO.getValor());
			lancamento.setConta(contaOrigem);
			
			if((planoConta.getTipoPlanoConta() == TipoPlanoConta.D) && contaOrigem.verificarSaldo(lancamentoDTO.getValor())) {
				contaOrigem.sacar(lancamento.getValor());
				lancamentoRepository.save(lancamento);
				
			}else if((planoConta.getTipoPlanoConta() == TipoPlanoConta.T && contaOrigem.verificarSaldo(lancamentoDTO.getValor()) && lancamento.getNumContaDest() != null)) {
				
				Conta contaDestino = contaRepository.findByNumero(lancamentoDTO.getNumConta());
				
				if(contaDestino != null) {
					lancamento.setNumContaDest(contaDestino.getNumero());
					
					contaOrigem.sacar(lancamentoDTO.getValor());
					contaDestino.depositar(lancamentoDTO.getValor());
					contaRepository.save(contaDestino);
				}
				
				throw new DataBaseException("Conta destino inexistente.");
				
			}else {
				contaOrigem.depositar(lancamento.getValor());
			}
			
			contaRepository.save(contaOrigem);
			
			
			return new LancamentoResponseDTO().fromDTO(lancamento);
			
		}
		
		throw new DataBaseException("Erro durante o cadastro de lançamento.");
		
	}
}