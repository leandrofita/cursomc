package com.neioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neioalves.cursomc.domain.Endereco;
import com.neioalves.cursomc.repositories.EnderecoRepository;
import com.neioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repo;
	
	/*Tenta buscar um endereco por id, caso não consiga lança uma exceção personalizada*/
	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
		}

}
