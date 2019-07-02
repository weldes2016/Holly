package br.com.miklus.holly.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.miklus.holly.domain.Pedido;
import br.com.miklus.holly.repositories.PedidoRepository;
import br.com.miklus.holly.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não Encontrado! Id:" +id +" ,Tipo: "+ Pedido.class.getName()));
	}

}
