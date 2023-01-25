package br.com.bm.corretora.api.repository;

import br.com.bm.corretora.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByCpf(String cpf);
	Optional<Usuario> findByEmail(String email);
}
