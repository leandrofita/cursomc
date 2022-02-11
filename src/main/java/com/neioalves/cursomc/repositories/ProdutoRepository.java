package com.neioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neioalves.cursomc.domain.Produto;

/*Acessa dados e salva um objeto*/

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
