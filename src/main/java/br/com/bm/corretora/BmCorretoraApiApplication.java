package br.com.bm.corretora;

import br.com.bm.corretora.enums.ProdutoEnum;
import br.com.bm.corretora.enums.SeguradoraEnum;
import br.com.bm.corretora.model.Cliente;
import br.com.bm.corretora.model.Produto;
import br.com.bm.corretora.repository.ClienteRepository;
import br.com.bm.corretora.repository.ProdutoRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

			clienteRepository.save(cliente);

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

			produtoList.add(produto);

			cliente.setProdutos(produtoList);

			produtoRepository.save(produto);

		};
	}

}
