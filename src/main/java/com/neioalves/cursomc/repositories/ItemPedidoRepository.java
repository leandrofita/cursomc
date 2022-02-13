package com.neioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neioalves.cursomc.domain.ItemPedido;

/*Acessa dados e salva um objeto*/

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

}
