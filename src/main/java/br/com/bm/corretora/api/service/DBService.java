package br.com.bm.corretora.api.service;

import br.com.bm.corretora.api.entity.Cliente;
import br.com.bm.corretora.api.entity.Produto;
import br.com.bm.corretora.api.enums.ProdutoEnum;
import br.com.bm.corretora.api.enums.SeguradoraEnum;
import br.com.bm.corretora.api.repository.ClienteRepository;
import br.com.bm.corretora.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class DBService {

	private final ClienteRepository clienteRepository;

	private final ProdutoRepository produtoRepository;

	@Autowired
	public DBService(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
		this.clienteRepository = clienteRepository;
		this.produtoRepository = produtoRepository;
	}

	public void instanciaDB() {
		clienteRepository.deleteAll();

		Set<Produto> produtoList = new HashSet<>();

		Cliente cliente = new Cliente();
		cliente.setNome("Lucas Brum");
		cliente.setDataNascimento(LocalDate.now());
		cliente.setEmail("lucas@gmail.com");
		cliente.setTelefone("21986604321");

		Cliente cliente2 = new Cliente();
		cliente2.setNome("Ester Finamore");
		cliente2.setDataNascimento(LocalDate.now());
		cliente2.setEmail("ester@gmail.com");
		cliente2.setTelefone("219845445366");

		Produto produto = new Produto();
		produto.setCliente(cliente);
		produto.setCoCorretagem(Boolean.TRUE);
		produto.setAgenciamentoPorcentagem(3.0);
		produto.setDataVigencia(LocalDate.now());
		produto.setSeguradora(SeguradoraEnum.PORTO.getNome());
		produto.setTipo(ProdutoEnum.AUTO.getNome());
		produto.setComissaoVendaPorcentagem(3.0);
		produto.setAgenciamentoPorcentagem(1.5);
		produto.setValorComissaoReceber(BigDecimal.valueOf(150.0));
		produto.setValorPremioLiquido(BigDecimal.valueOf(300.0));

		Produto produto2 = new Produto();
		produto2.setCliente(cliente2);
		produto2.setCoCorretagem(Boolean.TRUE);
		produto2.setAgenciamentoPorcentagem(20.0);
		produto2.setDataVigencia(LocalDate.now());
		produto2.setSeguradora(SeguradoraEnum.PORTO.getNome());
		produto2.setTipo(ProdutoEnum.AUTO.getNome());
		produto2.setComissaoVendaPorcentagem(2.0);
		produto2.setAgenciamentoPorcentagem(2.5);
		produto2.setValorComissaoReceber(BigDecimal.valueOf(1150.0));
		produto2.setValorPremioLiquido(BigDecimal.valueOf(3000.0));

		clienteRepository.save(cliente);
		clienteRepository.save(cliente2);
		produtoRepository.save(produto);
		produtoRepository.save(produto2);

	}
}
