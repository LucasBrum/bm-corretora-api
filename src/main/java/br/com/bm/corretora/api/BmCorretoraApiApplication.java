package br.com.bm.corretora.api;

import br.com.bm.corretora.api.repository.ClienteRepository;
import br.com.bm.corretora.api.repository.ProdutoRepository;
import br.com.bm.corretora.api.enums.ProdutoEnum;
import br.com.bm.corretora.api.enums.SeguradoraEnum;
import br.com.bm.corretora.api.entity.Cliente;
import br.com.bm.corretora.api.entity.Produto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BmCorretoraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmCorretoraApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
		return args -> {
			clienteRepository.deleteAll();

			Set<Produto> produtoList = new HashSet<>();

			Cliente cliente = new Cliente();
			cliente.setNome("Lucas Brum");
			cliente.setDataNascimento(LocalDate.now());
			cliente.setEmail("lucas@gmail.com");
			cliente.setTelefone("21986604321");

			//clienteRepository.save(cliente);

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
			produto2.setCliente(cliente);
			produto2.setCoCorretagem(Boolean.TRUE);
			produto2.setAgenciamentoPorcentagem(3.0);
			produto2.setDataVigencia(LocalDate.now());
			produto2.setSeguradora(SeguradoraEnum.PORTO.getNome());
			produto2.setTipo(ProdutoEnum.AUTO.getNome());
			produto2.setComissaoVendaPorcentagem(3.0);
			produto2.setAgenciamentoPorcentagem(1.5);
			produto2.setValorComissaoReceber(BigDecimal.valueOf(150.0));
			produto2.setValorPremioLiquido(BigDecimal.valueOf(300.0));

			clienteRepository.save(cliente);
			produtoRepository.save(produto);
			produtoRepository.save(produto2);



		};
	}

}
