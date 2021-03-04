package br.com.gama.bankline.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gama.bankline.model.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	@Query("Select L from Lancamento L where L.numConta = ?1 and L.data >= ?2 and L.data <= ?3")
	@Transactional()
	List<Lancamento> findByDatas(String numeroConta, LocalDate dataInicio, LocalDate dataFim);

	@Transactional()
	List<Lancamento> findByConta(String numConta);
	
	List<Lancamento> findByContaIdOrderByDataDesc(Long contaId);
	
	List<Lancamento> findByDataGreaterThanEqualAndDataLessThanEqualAndNumContaOrderByDataDesc(LocalDate data, LocalDate data1, String numConta);

}
