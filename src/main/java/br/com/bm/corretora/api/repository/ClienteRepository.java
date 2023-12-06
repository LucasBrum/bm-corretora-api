package br.com.bm.corretora.api.repository;

import br.com.bm.corretora.api.entity.Cliente;
import br.com.bm.corretora.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByCpf(String cpf);
	Optional<Cliente> findByEmail(String email);
	List<Cliente> findAllByOrderByNomeAsc();
}
