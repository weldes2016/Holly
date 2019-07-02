package br.com.miklus.holly.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miklus.holly.domain.Cidade;
import br.com.miklus.holly.repositories.CidadeRepository;
import br.com.miklus.holly.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Cidade find(Integer id) {
		
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não Encontrado! Id:" +id +" ,Tipo: "+ Cidade.class.getName()));
	}

}
