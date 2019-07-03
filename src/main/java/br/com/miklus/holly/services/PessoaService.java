package br.com.miklus.holly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.miklus.holly.domain.Pessoa;
import br.com.miklus.holly.dto.PessoaDTO;
import br.com.miklus.holly.repositories.PessoaRepository;
import br.com.miklus.holly.services.exceptions.DateIntegrityException;
import br.com.miklus.holly.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repo;

	public Pessoa find(Integer id) {

		Optional<Pessoa> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não Encontrado! Id:" + id + " ,Tipo: " + Pessoa.class.getName()));
	}

	public Pessoa insert(Pessoa obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Pessoa update(Pessoa obj) {
		Pessoa newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DateIntegrityException("Não é Possivel excluir porque tem entidades relacionadas");
		}
	}

	public List<Pessoa> findAll() {
		return repo.findAll();
	}

	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}

	public Pessoa fromDTO(PessoaDTO objDto) {

		return new Pessoa(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);

	}

	private void updateData(Pessoa newObj, Pessoa obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}
}
