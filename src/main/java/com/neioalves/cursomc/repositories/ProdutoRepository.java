package com.neioalves.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neioalves.cursomc.domain.Categoria;
import com.neioalves.cursomc.domain.Produto;

/*Acessa dados e salva um objeto*/

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Transactional(readOnly = true)
	//Essa anotação permite implementar consultas JPQL direto no repository se necessidade da criação de outra classe
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	
	//Anotação @Param serve para "casar" os parâmetros da query com os parâmetros do método
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias")List<Categoria> categorias, Pageable pageRequest);
	
	//findDistinctByNomeContainingAndCategoriasIn -> nome gerado através do sitema de nomes do framework para gerar consultas automáticas

}
