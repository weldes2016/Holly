package com.weldes.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.weldes.cursomc.domain.Categoria;
import com.weldes.cursomc.domain.Cidade;
import com.weldes.cursomc.domain.Cliente;
import com.weldes.cursomc.domain.Endereco;
import com.weldes.cursomc.domain.Estado;
import com.weldes.cursomc.domain.Produto;
import com.weldes.cursomc.domain.enums.TipoCliente;
import com.weldes.cursomc.repositories.CategoriaRepository;
import com.weldes.cursomc.repositories.CidadeRepository;
import com.weldes.cursomc.repositories.ClienteRepository;
import com.weldes.cursomc.repositories.EnderecoRepository;
import com.weldes.cursomc.repositories.EstadoRepository;
import com.weldes.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@miklus.com.br", "61900320100", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("39565500","982592220"));
		
		Endereco e1 = new Endereco(null,"Rua Flores", "200","Qd 436, Lt. 01", "Jardim Amercia", "74250-010",cli1, c1);
		Endereco e2 = new Endereco(null,"Av. Matos", "105","Qd 436, Lt. 01", "Jardim Amercia", "74250-010",cli1, c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
		
	}

}
