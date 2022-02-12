package com.neioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neioalves.cursomc.domain.Cidade;

/*Acessa dados e salva um objeto*/

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
