package br.com.bm.corretora.api.service.impl;

import br.com.bm.corretora.api.dto.ProdutoDTO;
import br.com.bm.corretora.api.entity.Cliente;
import br.com.bm.corretora.api.entity.Produto;
import br.com.bm.corretora.api.service.ClienteService;
import br.com.bm.corretora.api.service.ProdutoService;
import br.com.bm.corretora.api.exception.ObjectNotFoundException;
import br.com.bm.corretora.api.util.Messages;
import br.com.bm.corretora.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private final ProdutoRepository produtoRepository;

	private final ClienteService clienteService;

	@Autowired
	public ProdutoServiceImpl(ProdutoRepository produtoRepository, ClienteService clienteService) {
		this.produtoRepository = produtoRepository;
		this.clienteService = clienteService;
	}

	@Override
	public Produto findById(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(Messages.PRODUTO_NAO_ENCONTRADO));
	}

	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto create(ProdutoDTO produtoDTO) {
		Cliente cliente = clienteService.findById(produtoDTO.getIdCliente());

		produtoDTO.setId(null);

		Produto produto = new Produto(produtoDTO);
		produto.setCliente(cliente);
		return produtoRepository.save(produto);
	}

	@Override
	public Produto update(Long id, @Valid ProdutoDTO produtoDTO) {
		produtoDTO.setId(id);

		Produto oldProduto = findById(id);
		Produto produto = new Produto();
		if (oldProduto != null) {
			produto.setId(produtoDTO.getId());
			produto.setCoCorretagem(produtoDTO.getCoCorretagem());
			produto.setAgenciamentoPorcentagem(produtoDTO.getAgenciamentoPorcentagem());
			produto.setComissaoVendaPorcentagem(produtoDTO.getComissaoVendaPorcentagem());
			produto.setDataVigencia(produtoDTO.getDataVigencia());
			produto.setSeguradora(produtoDTO.getSeguradora());
			produto.setTipo(produtoDTO.getTipo());
			produto.setValorComissaoReceber(produtoDTO.getValorComissaoReceber());
			produto.setValorPremioLiquido(produtoDTO.getValorPremioLiquido());
			produto.setCliente(oldProduto.getCliente());

			produtoRepository.save(produto);
		}

		return produto;
	}

}
