package br.com.bm.corretora.api.service.impl;

import br.com.bm.corretora.api.dto.UsuarioDTO;
import br.com.bm.corretora.api.entity.Usuario;
import br.com.bm.corretora.api.service.UsuarioService;
import br.com.bm.corretora.api.exception.DataIntegrityViolationException;
import br.com.bm.corretora.api.exception.ObjectNotFoundException;
import br.com.bm.corretora.api.repository.UsuarioRepository;
import br.com.bm.corretora.api.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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

		return cliente.orElseThrow(() -> new ObjectNotFoundException(Messages.USUARIO_NAO_ENCONTRADO));
	}

	@Override
	public List<Usuario> findAll() {

		return usuarioRepository.findAll();
	}

	@Override
	public Usuario create(UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(null);

		validaCPF(usuarioDTO);
		validaEmail(usuarioDTO);

		Usuario usuario = new Usuario(usuarioDTO);
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario update(Long id, @Valid UsuarioDTO usuarioDTO) {
		usuarioDTO.setId(id);

		Usuario usuarioEncontrado = findById(id);

		validaCPF(usuarioDTO);
		validaEmail(usuarioDTO);

		usuarioEncontrado = new Usuario(usuarioDTO);

		return usuarioRepository.save(usuarioEncontrado);
	}

	@Override
	public void delete(Long id) {
		Usuario usuarioEncontrado = findById(id);

		usuarioRepository.deleteById(id);
	}

	private void validaCPF(UsuarioDTO usuarioDTO) {
		Optional<Usuario> usuario = usuarioRepository.findByCpf(usuarioDTO.getCpf());
		if (usuario.isPresent() && usuario.get().getId() != usuarioDTO.getId()) {
			throw new DataIntegrityViolationException(Messages.CPF_JA_CADASTRADO);
		}
	}

	private void validaEmail(UsuarioDTO usuarioDTO) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());
		if (usuario.isPresent() && usuario.get().getId() != usuarioDTO.getId()) {
			throw new DataIntegrityViolationException(Messages.EMAIL_JA_CADASTRADO);
		}
	}
}
