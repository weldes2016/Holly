package br.com.miklus.holly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.miklus.holly.domain.Ministerio;
import br.com.miklus.holly.dto.MinisterioDTO;
import br.com.miklus.holly.repositories.MinisterioRepository;
import br.com.miklus.holly.services.exceptions.DateIntegrityException;
import br.com.miklus.holly.services.exceptions.ObjectNotFoundException;

@Service
public class MinisterioService {

	@Autowired
	private MinisterioRepository repo;

	public Ministerio find(Integer id) {

		Optional<Ministerio> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não Encontrado! Id:" + id + " ,Tipo: " + Ministerio.class.getName()));
	}

	public Ministerio insert(Ministerio obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Ministerio update(Ministerio obj) {
		Ministerio newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DateIntegrityException("Não é Possivel excluir uma categoria que possui produtos");
		}	
	}

	public List<Ministerio> findAll() {
		return repo.findAll();
	}
	
	public Page<Ministerio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	public Ministerio fromDTO(MinisterioDTO objDto) {
		return new Ministerio(objDto.getId(), objDto.getNome()); 
		
	}

	private void updateData(Ministerio newObj, Ministerio obj) {
		newObj.setNome(obj.getNome());
		
	}
}
