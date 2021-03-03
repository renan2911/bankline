package br.com.gama.bankline.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.gama.bankline.model.PlanoConta;

@Repository
public interface PlanoContaRepository extends JpaRepository<PlanoConta, Long>{

	@Transactional(readOnly = true)
	Optional<PlanoConta> findById(Long id);
	
	@Query("SELECT pl FROM PlanoConta pl WHERE usuario.login = :login ")
	List<PlanoConta> findDistinctPeopleByLastnameOrFirstname(@Param("login") String login);
}

/*
 * SELECT plano FROM PlanoConta plano 
JOIN PlanoConta.Usuario usuario
WHERE usuarion.login = :login/ 
 */