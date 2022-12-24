package br.com.bm.corretora.api.service.impl;

import br.com.bm.corretora.api.dto.ProdutoDTO;
import br.com.bm.corretora.api.entity.Produto;
import br.com.bm.corretora.api.exception.ObjectNotFoundException;
import br.com.bm.corretora.api.repository.ProdutoRepository;
import br.com.bm.corretora.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Override
	public Produto findById(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado."));
	}

	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto create(ProdutoDTO produtoDTO) {
		produtoDTO.setId(null);
		Produto produto = new Produto(produtoDTO);
		return produtoRepository.save(produto);
	}
}
