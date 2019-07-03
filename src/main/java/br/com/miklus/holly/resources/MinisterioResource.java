package br.com.miklus.holly.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.miklus.holly.domain.Ministerio;
import br.com.miklus.holly.dto.MinisterioDTO;
import br.com.miklus.holly.services.MinisterioService;

@RestController
@RequestMapping(value = "/ministerios/")
public class MinisterioResource {

	@Autowired
	private MinisterioService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Ministerio> find(@PathVariable Integer id) {

		Ministerio obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MinisterioDTO objDto) {
		Ministerio obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody MinisterioDTO objDto, @PathVariable Integer id) {
		Ministerio obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MinisterioDTO>> findAll() {
		List<Ministerio> list=service.findAll();
		List<MinisterioDTO> listDto = list.stream().map(obj -> new MinisterioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	
	@RequestMapping(value ="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<MinisterioDTO>> findPage(
			@RequestParam(value="page", defaultValue="0")Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		Page<Ministerio> list=service.findPage(page, linesPerPage, orderBy, direction);
		Page<MinisterioDTO> listDto = list.map(obj -> new MinisterioDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
