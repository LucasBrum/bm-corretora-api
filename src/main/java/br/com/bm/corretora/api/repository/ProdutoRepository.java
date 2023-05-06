package br.com.bm.corretora.api.repository;

import br.com.bm.corretora.api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findProdutosByClienteId(Long idCliente);
}
