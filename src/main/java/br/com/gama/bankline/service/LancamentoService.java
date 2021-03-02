package br.com.gama.bankline.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.gama.bankline.model.Lancamento;
import br.com.gama.bankline.repository.LancamentoRepository;


@Service
public class LancamentoService {
	@Autowired
	private LancamentoRepository lancamentoRepository;

	
	public void salvarLancamento(Lancamento lancamento) {
		
		lancamentoRepository.save(lancamento);
	}
	
	public List<Lancamento> getLancamentosByData(String numeroConta,LocalDate LocalDate, LocalDate dataInicio,
			@RequestParam LocalDate dataFim) {
			return lancamentoRepository.findByDatas(numeroConta, dataInicio, dataFim);
	}
}