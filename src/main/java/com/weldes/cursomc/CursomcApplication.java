package com.weldes.cursomc;

import java.text.SimpleDateFormat;
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
import com.weldes.cursomc.domain.ItemPedido;
import com.weldes.cursomc.domain.Pagamento;
import com.weldes.cursomc.domain.PagamentoComBoleto;
import com.weldes.cursomc.domain.PagamentoComCartao;
import com.weldes.cursomc.domain.Pedido;
import com.weldes.cursomc.domain.Produto;
import com.weldes.cursomc.domain.enums.EstadoPagamento;
import com.weldes.cursomc.domain.enums.TipoCliente;
import com.weldes.cursomc.repositories.CategoriaRepository;
import com.weldes.cursomc.repositories.CidadeRepository;
import com.weldes.cursomc.repositories.ClienteRepository;
import com.weldes.cursomc.repositories.EnderecoRepository;
import com.weldes.cursomc.repositories.EstadoRepository;
import com.weldes.cursomc.repositories.ItemPedidoRepository;
import com.weldes.cursomc.repositories.PagamentoRepository;
import com.weldes.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cozinha");
		Categoria cat4 = new Categoria(null, "Cama mesa e banho");
		Categoria cat5 = new Categoria(null, "Churrasco");
		Categoria cat6 = new Categoria(null, "Eletronicos");
		Categoria cat7 = new Categoria(null, "Plantas");
		Categoria cat8 = new Categoria(null, "Higiene e Limpeza");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3,cat4, cat5, cat6, cat7, cat8));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("28/06/2019 10:35"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("28/06/2019 10:40"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("28/06/2019 10:41"),null );
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
