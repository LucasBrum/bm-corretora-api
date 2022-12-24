package br.com.bm.corretora.api.service.impl;

import br.com.bm.corretora.api.dto.ClienteDTO;
import br.com.bm.corretora.api.entity.Cliente;
import br.com.bm.corretora.api.exception.ObjectNotFoundException;
import br.com.bm.corretora.api.repository.ClienteRepository;
import br.com.bm.corretora.api.service.ClienteService;
import br.com.bm.corretora.api.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;

	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Cliente findById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		return cliente.orElseThrow(() -> new ObjectNotFoundException(Messages.CLIENTE_NAO_ENCONTRADO));
	}

	@Override
	public List<Cliente> findAll() {

		return clienteRepository.findAll();
	}

	@Override
	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		Cliente cliente = new Cliente(clienteDTO);
		return clienteRepository.save(cliente);
	}
}
