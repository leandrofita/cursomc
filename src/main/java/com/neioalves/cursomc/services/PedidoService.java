package com.neioalves.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neioalves.cursomc.domain.ItemPedido;
import com.neioalves.cursomc.domain.PagamentoComBoleto;
import com.neioalves.cursomc.domain.Pedido;
import com.neioalves.cursomc.domain.enums.EstadoPagamento;
import com.neioalves.cursomc.repositories.ItemPedidoRepository;
import com.neioalves.cursomc.repositories.PagamentoRepository;
import com.neioalves.cursomc.repositories.PedidoRepository;
import com.neioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	@Autowired
	private ClienteService clienteService;
	
	/*Tenta buscar uma categoria por id, caso não consiga lança uma exceção personalizada*/
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
	
	//Método para inserção de um pedido
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		//instanceof is a binary operator used to test if an object is of a given type. The result of the operation is either true or false.
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			//casting do pagto para o tipo PagamentoComBoleto
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentocomBoleto(pagto, obj.getInstante());
			
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			//obtendo o preço dos produtos
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		return obj;
	}

}
