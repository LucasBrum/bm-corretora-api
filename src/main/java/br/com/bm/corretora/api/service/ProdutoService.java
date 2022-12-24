package br.com.bm.corretora.api.service;

import br.com.bm.corretora.api.dto.ProdutoDTO;
import br.com.bm.corretora.api.entity.Produto;

import java.util.List;

public interface ProdutoService {
	Produto findById(Long id);

	List<Produto> findAll();

	Produto create(ProdutoDTO produtoDTO);
}
