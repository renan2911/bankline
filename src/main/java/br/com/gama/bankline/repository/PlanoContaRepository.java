package br.com.gama.bankline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.gama.bankline.model.PlanoConta;

@Repository
public interface PlanoContaRepository extends JpaRepository<PlanoConta, Long>{

	@Transactional(readOnly = true)
	PlanoConta findByLogin(String login);
	
}
