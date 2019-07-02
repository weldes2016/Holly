package br.com.miklus.holly.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.miklus.holly.domain.Cidade;
import br.com.miklus.holly.services.CidadeService;

@RestController
@RequestMapping(value="/cidades/")
public class CidadeResource {
	
	@Autowired
	private CidadeService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	
	public ResponseEntity<Cidade> find(@PathVariable Integer id) {
		
		Cidade obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}

}
