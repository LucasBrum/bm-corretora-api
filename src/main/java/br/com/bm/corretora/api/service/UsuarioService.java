package br.com.bm.corretora.api.service;

import br.com.bm.corretora.api.dto.UsuarioDTO;
import br.com.bm.corretora.api.entity.Usuario;

import java.util.List;

public interface UsuarioService {

	Usuario findById(Long id);

	List<Usuario> findAll();

	Usuario create(UsuarioDTO usuarioDTO);

	Usuario update(Long id, UsuarioDTO usuarioDTO);
}
