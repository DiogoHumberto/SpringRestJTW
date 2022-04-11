package br.com.SpringRestJWT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.SpringRestJWT.domain.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	Pessoa findByEmail(String email);
	
	boolean existsByEmail(String email);
}
