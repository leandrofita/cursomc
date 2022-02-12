package com.neioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neioalves.cursomc.domain.Pagamento;

/*Acessa dados e salva um objeto*/
//não é necessário criar repositories para subclasses

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
