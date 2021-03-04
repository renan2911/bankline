package br.com.gama.bankline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.gama.bankline.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	@Transactional(readOnly = true)
	Conta findByNumero(String numero);
}
