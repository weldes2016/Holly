package br.com.miklus.holly.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.miklus.holly.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	
	@NotEmpty (message="Preenchimento Obrigatório")
	@Length(min=5,max=80, message="O Tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Categoria obj) {
		Id = obj.getId();
		nome = obj.getNome();	
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
