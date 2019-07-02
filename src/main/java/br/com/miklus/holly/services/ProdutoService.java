package br.com.miklus.holly.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miklus.holly.domain.Produto;
import br.com.miklus.holly.repositories.ProdutoRepository;
import br.com.miklus.holly.services.exceptions.ObjectNotFoundException;

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
