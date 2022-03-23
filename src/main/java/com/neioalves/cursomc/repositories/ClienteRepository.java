package com.neioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neioalves.cursomc.domain.Cliente;

/*Acessa dados e salva um objeto*/

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	// deixa a operação mais rápida e não necessita que a operação seja envolvida em uma transação de banco de dados
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);

}
