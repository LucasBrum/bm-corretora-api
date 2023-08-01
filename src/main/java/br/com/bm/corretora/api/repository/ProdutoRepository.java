package br.com.bm.corretora.api.repository;

import br.com.bm.corretora.api.entity.Produto;
import br.com.bm.corretora.api.model.response.QuantidadeProdutosPorTipoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findProdutosByClienteId(Long idCliente);

	@Query("SELECT new br.com.bm.corretora.api.model.response.QuantidadeProdutosPorTipoResponse(p.tipo, count(p)) FROM Produto p GROUP BY p.tipo")
	List<QuantidadeProdutosPorTipoResponse> getQuantidadeProdutosPorTipo();

}
