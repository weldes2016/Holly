package com.weldes.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weldes.cursomc.domain.Produto;
import com.weldes.cursomc.repositories.ProdutoRepository;
import com.weldes.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	public Produto buscar(Integer id) {
		
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não Encontrado! Id:" +id +" ,Tipo: "+ Produto.class.getName()));
	}

}
