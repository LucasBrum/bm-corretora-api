package br.com.bm.corretora.api.service;

import br.com.bm.corretora.api.dto.ProdutoDTO;
import br.com.bm.corretora.api.entity.Produto;
import br.com.bm.corretora.api.model.response.QuantidadeProdutosPorTipoResponse;

import java.util.List;

public interface ProdutoService {
	Produto findById(Long id);

	List<Produto> findAll();

	Produto create(ProdutoDTO produtoDTO);

	Produto update(Long id, ProdutoDTO produtoDTO);

	List<Produto> findProdutosByClienteId(Long idCliente);

	List<QuantidadeProdutosPorTipoResponse> getQuantidadeProdutosPorTipo();

	void delete(Long id);

}
