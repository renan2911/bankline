package br.com.gama.bankline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.gama.bankline.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
	
	@Transactional(readOnly = true)
	Conta findByNumero(String numero);
	
	@Query("SELECT ct FROM Conta ct WHERE usuario.login = :login")
	Optional<Conta> findDistinctPeopleByLastnameOrFirstname(@Param("login") String login);
}
