package br.com.bm.corretora.api.service.impl;

import br.com.bm.corretora.api.dto.UsuarioDTO;
import br.com.bm.corretora.api.entity.Usuario;
import br.com.bm.corretora.api.exception.ObjectNotFoundException;
import br.com.bm.corretora.api.repository.UsuarioRepository;
import br.com.bm.corretora.api.service.UsuarioService;
import br.com.bm.corretora.api.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario findById(Long id) {
		Optional<Usuario> cliente = usuarioRepository.findById(id);

		return cliente.orElseThrow(() -> new ObjectNotFoundException(Messages.CLIENTE_NAO_ENCONTRADO));
	}

	@Override
	public List<Usuario> findAll() {

		return usuarioRepository.findAll();
	}

	@Override
	public Usuario create(UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(null);
		Usuario cliente = new Usuario(usuarioDTO);
		return usuarioRepository.save(cliente);
	}
}
